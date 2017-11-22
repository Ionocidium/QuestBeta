package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import configuration.LocalConfiguration;
import service.ClientService;
import controller.CommandLineControls;
import controller.fileops.FileLoad;
import controller.fileops.FileSave;
import model.ErrorLog;

/**
 * This class is used for editing the file path of PDE-C’s compiler since the default compiler can be changed by other developers. Another use of this class is
 * for compiling a source code given a file path.
 * @author Alexander John D. Jose
 * 
 * Tailored to work for QUEST
 */

public class ECompiler {

	private static EventController instance = null;
	private final JFileChooser fileChooser;
	private FileLoad loader;
	private FileSave saveFile;
	private FileNameExtensionFilter cFilter;
	private String command = "";

	public ECompiler() {
		saveFile = new FileSave();
		loader = new FileLoad();
		fileChooser = new JFileChooser();

		cFilter = new FileNameExtensionFilter("C Source (*.c)", "c");
		fileChooser.setFileFilter(cFilter);
	}

	/**
	 * Checks if PDE-C is pointing to the correct path of the machine's gcc.exe
	 * @return true if PDE-C found gcc.exe, false if not
	 */
	private boolean checkIfGccExists() {
		boolean itExists = false;

		LocalConfiguration local = LocalConfiguration.getInstance();

		if (Files.exists(Paths.get("C:\\Program Files (x86)\\Dev-Cpp\\MinGW64\\bin\\gcc.exe"))) {
			itExists = true;
			//System.out.println("it exists!!!\n");
			//LocalConfiguration local = LocalConfiguration.getInstance();

			Path path = Paths.get("C:\\Program Files (x86)\\Dev-Cpp\\MinGW64\\bin\\gcc.exe");
			local.setGccPath(path.toAbsolutePath().toString());	
		}

		else {
			JOptionPane.showMessageDialog(null, "PDE-C cannot find the gcc.exe specified. Please use the settings to fix the path.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return itExists;
	}

	/**
	 * Compiles the source code.
	 * <p>
	 *  It checks if the compiler exists. Otherwise, compilation of the source code will not work. As a result, it is imperative to open a source code first before compiling. Otherwise, opening the file will be done by the time a student presses Compile button.
	 * </p>
	 * 
	 * @param frame The target frame to modify.
	 * @param editorPane The <code>editorPane</code> to use.
	 * @param filePath The source code to compile.
	 * @param compileLog The <code>textArea</code> to use for logging errors.
	 * @return the <code>filePath</code> to compile
	 */
	public Path compile(Path filePath, ErrorLog errorlog) {

		Path path = null;

		if (this.checkIfGccExists()) {
			try {
				if (filePath != null) {
					path = filePath;
					CommandLineControls clc = new CommandLineControls(filePath.toString());

					if (!clc.getStdOut().equals("")) {
						errorlog.setErrorLog((clc.getStdOut() + "\n" + clc.getStdError() + "\n").toString());
						//compileLog.setText(clc.getStdOut() + "\n" + clc.getStdError() + "\n");
					}
					else {
						errorlog.setErrorLog((clc.getStdError() + "\n").toString());
						//compileLog.setText(clc.getStdError() + "\n");
					}
					
					if (!clc.getStdError().equals("")) {
						// this.deleteDontTouch();
						clc.runMyCompiler();
					}
				}

			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return path;
	}

	/**
	 * Prototype Version of compile method.
	 * <p>
	 *  This should pop up a <code>CompileLog</code> in a separate window.
	 * </p>
	 * 
	 * @param p The source code to use.
	 */
	public void runProgram(Path p) {
		try {
			File f = new File(p.toString());
			String dir = f.getParent();
			String filename = f.getName();
			String currentOS = System.getProperty("os.name").toLowerCase();

			//System.out.println("OS: " + currentOS);
			
			if (currentOS.indexOf("win") >= 0) {
				String compiled = dir.concat("\\").concat(filename.substring(0, f.getName().lastIndexOf(".")).concat(".exe"));
				
				//System.out.println("Compiled File: " + compiled);

				if (this.programIfExists(compiled)) {
					System.out.println("Finished Compiling");
					ProcessBuilder pb = new ProcessBuilder("cmd", "/k", "start", compiled);
					Process proc = pb.start();
				}
				else {
					JOptionPane.showMessageDialog(null, "Some compilation error occured", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			else if(currentOS.indexOf("mac") >= 0) {
				String compiled = dir.concat("\\").concat(filename.substring(0, f.getName().lastIndexOf(".")).concat(".out"));
				Runtime rt = Runtime.getRuntime();
				Process proc = rt.exec(compiled);
			}

			else if(currentOS.indexOf("nix") >= 0 || currentOS.indexOf("nux") >= 0) {
				String compiled = dir.concat("\\").concat(filename.substring(0, f.getName().lastIndexOf(".")));
				Runtime rt = Runtime.getRuntime();
				Process proc = rt.exec(compiled);
			}
		}

		catch(Exception ex) {
			System.out.println("");
		}
	}

	/**
	 * Checks if the corresponding executable file exists
	 * @param compiledPath the path of the execubtable file to be checked
	 * @return <code>true</code> if PDE-C found the executable file, <code>false</code>	 if not
	 */
	private boolean programIfExists(String compiledPath) {
		boolean itExists = false;

		if (Files.exists(Paths.get(compiledPath))) {
			itExists = true;
		}

		return itExists;
	}

}
