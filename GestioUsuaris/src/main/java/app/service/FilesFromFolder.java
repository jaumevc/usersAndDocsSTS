package app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.dao.DocumentDAO;
import app.dao.UserDAO;

public class FilesFromFolder {

	private static final String RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\PROVES";
	private static final String CORRECT_FILES_FOLDER = "\\\\sarroca\\comu-inf$\\Suport\\test\\PROVES\\CARREGA";

	private static final String ERROR_FILES_FOLDER = "\\\\sarroca\\comu-inf$\\Suport\\test\\PROVES\\ERROR";

	private File ruthFilesFolders = new File(RUTH_NAME);
	private List<File> onlyFilesInRuth = new ArrayList<>();

	private File errorFilesFolders = new File(ERROR_FILES_FOLDER);
	private List<File> onlyFilesInError = new ArrayList<>();

	public FilesFromFolder(Integer tipusFitxer) {
		if(tipusFitxer != null && tipusFitxer == 1) {
			// fitxers en arrel
			this.onlyFilesInRuth = getOnlyFiles(tipusFitxer);
		}

		if(tipusFitxer != null && tipusFitxer == 2) {
			// fitxers eb carpeta errors
			this.onlyFilesInError = getOnlyFiles(tipusFitxer);
		}
	}

	private List<File> getOnlyFiles(Integer tipusFitxer) {
		List<File> filesInPath = new ArrayList<>();
		
		if(tipusFitxer != null && tipusFitxer == 1) {
			filesInPath = Arrays.asList(ruthFilesFolders.listFiles());
		} else if (tipusFitxer != null && tipusFitxer == 2) {
			filesInPath =Arrays.asList(errorFilesFolders.listFiles());
		}

		List<File> onlyFilesInPath = new ArrayList<>();

		for (File f : filesInPath) {
			if (!f.isDirectory())
				onlyFilesInPath.add(f);
		}

		return onlyFilesInPath;
	}

	public Set<String> getDnisFromFilesTitle(Integer tipusFitxer) {
		Set<String> dnisFromFilesTitle = new HashSet<>();
		List<String> fileNames = new ArrayList<>();
		
		if (tipusFitxer != null && tipusFitxer == 1) {
			fileNames = getFileNames(tipusFitxer);
			for (String n : fileNames) {
				dnisFromFilesTitle.add(n.substring(0, 9));
			}
				
		} else if(tipusFitxer != null && tipusFitxer == 2) {
			fileNames = getFileNames(tipusFitxer);
			for (String n : fileNames) {
				dnisFromFilesTitle.add(n.substring(0, 9));
			}
				
		}
		
		return dnisFromFilesTitle;
	}

	public List<String> getFileNames(Integer tipusFitxer) {
		List<String> fileNames = new ArrayList<>();
		
		if (tipusFitxer != null && tipusFitxer == 1) {
			if (!onlyFilesInRuth.isEmpty()) {
				for (File f : onlyFilesInRuth) {
					if (!f.isDirectory())
						fileNames.add(f.getName());
				}
			}
		}else if(tipusFitxer != null && tipusFitxer == 2) {
			if (!onlyFilesInError.isEmpty()) {
				for (File f : onlyFilesInError) {
					if (!f.isDirectory())
						fileNames.add(f.getName());
				}
			}
		}

		return fileNames;
	}

	// fitxer en floder arrel = 1; fitxer en folder error = 2
	public Map<Integer, List<File>> checkFiles(Integer tipusFitxer) {
		Map<Integer, List<File>> fileMatrix = new HashMap<>();
		Map<String, String> loginsByNif = new HashMap<>();

		Set<String> dnisInFilesTitle = new HashSet<>();
		
		if (tipusFitxer != null && tipusFitxer == 1) {

			dnisInFilesTitle = getDnisFromFilesTitle(tipusFitxer);

			if (!dnisInFilesTitle.isEmpty()) {
				loginsByNif = getLoginsByNif(dnisInFilesTitle);
			}

			if (!loginsByNif.isEmpty()) {
				fileMatrix = saveAndMoveFiles(loginsByNif, tipusFitxer);
			}

		} else if(tipusFitxer != null && tipusFitxer == 2) {
			
			dnisInFilesTitle = getDnisFromFilesTitle(tipusFitxer);
			
			if (!dnisInFilesTitle.isEmpty()) {
				loginsByNif = getLoginsByNif(dnisInFilesTitle);
			}
			
			if (!loginsByNif.isEmpty()) {
				fileMatrix = saveAndMoveFiles(loginsByNif, tipusFitxer);
			}
		}
		
		return fileMatrix;
	}

	private Map<String, String> getLoginsByNif(Set<String> dnis) {
		UserDAO daoUser = new UserDAO();
		Map<String, String> loginsByUser = new HashMap<>();
		try {
			loginsByUser = daoUser.getLoginByUser(dnis);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return loginsByUser;
	}

	private Map<Integer, List<File>> saveAndMoveFiles(Map<String, String> loginsByNif, Integer tipusFitxer) {
		
		List<File> correctFilesToMove = new ArrayList<>();
		List<File> filesWithErrorToMove = new ArrayList<>();
		Map<Integer, List<File>> matrixFiles = new HashMap<>();

		if(tipusFitxer !=null && tipusFitxer == 1) {
			for(File file: onlyFilesInRuth) {
				if(loginsByNif.containsKey(file.getName().substring(0, 9))) {
					saveFiles(file, loginsByNif);
					correctFilesToMove.add(file);
				}else {
					filesWithErrorToMove.add(file);
				}
			}
			
			// CHECK IF I GOT SAVED FILES CORRECTLY. PUT PDF FILE IN DESKTOP LIKE jaumet.pdf
//			try {
//				docDao.gedPdfSaved();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			try {
				moveCorrectFiles(correctFilesToMove);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				moveErrorFiles(filesWithErrorToMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			matrixFiles.put(1, correctFilesToMove);
			matrixFiles.put(2, filesWithErrorToMove);
			
		}else if(tipusFitxer !=null && tipusFitxer == 2) {
			
			for(File file: onlyFilesInError) {
				if(loginsByNif.containsKey(file.getName().substring(0, 9))) {
					saveFiles(file, loginsByNif);
					correctFilesToMove.add(file);
				}else {
					filesWithErrorToMove.add(file);
				}
			}
			
			try {
				moveErrorFilesToCorrectFolder(correctFilesToMove);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			matrixFiles.put(1, correctFilesToMove);
			matrixFiles.put(2, filesWithErrorToMove);
			
		}
			
		return matrixFiles;
	}

	private void saveFiles(File file, Map<String, String> loginsByNif) {
		DocumentDAO docDao = new DocumentDAO();
		String year = file.getName().substring(22, 26);
		String month = file.getName().substring(26, 28);
		String monthName = getMonthName(month);
		try {
			docDao.saveCorrectFiles(file, loginsByNif.get(file.getName().substring(0, 9)), monthName, year);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void moveErrorFilesToCorrectFolder(List<File> correctFilesToMove) throws IOException {
		for (File file : correctFilesToMove) {
			Path temp = Files.move(Paths.get(ERROR_FILES_FOLDER + "\\" + file.getName()),
					Paths.get(CORRECT_FILES_FOLDER + "\\" + file.getName()));
			if (temp != null) {
				System.out.println("File renamed and moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}
		}
	}

	private void moveCorrectFiles(List<File> correctFilesToMove) throws IOException {
		for (File file : correctFilesToMove) {
			Path temp = Files.move(Paths.get(RUTH_NAME + "\\" + file.getName()),
					Paths.get(CORRECT_FILES_FOLDER + "\\" + file.getName()));
			if (temp != null) {
				System.out.println("File renamed and moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}
		}
	}

	private void moveErrorFiles(List<File> filesWithErrorToMove) throws IOException {
		for (File file : filesWithErrorToMove) {
			Path temp = Files.move(Paths.get(RUTH_NAME + "\\" + file.getName()),
					Paths.get(ERROR_FILES_FOLDER + "\\" + file.getName()));
			if (temp != null) {
				System.out.println("File renamed and moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}
		}
	}

	private String getMonthName(String month) {
		String monthName = "";
		Mesos[] mesos = Mesos.values();

		for (Mesos m : mesos) {
			if (m.getValue() == Integer.valueOf(month))
				monthName = m.name().toString();
		}
		return monthName;
	}
}
