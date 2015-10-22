package com.xiaba2.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class SchemaFileUtil {

	/**
	 * 模块文件生成类。 当domain中有实体类时，会自动生成Service Dao Impl Controller
	 */
	private HashMap<String, String> moduleMap = new HashMap<String, String>();

	/**
	 * 系统分隔符
	 */
	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String LINE = System.getProperty("line.separator");
	private static final String DOMAIN = "domain";
	private static final String DAO = "dao";
	private static final String SERVICE = "service";
	private static final String IMPL = "impl";
	private static final String CONTROLLER = "controller";
	private static final String EXT = ".java";

	public static void main(String[] args) {
		SchemaFileUtil sfu = new SchemaFileUtil();
		sfu.getAllModules();
		sfu.genDir();
		sfu.genFile();
	}

	/**
	 * 识别所有模块
	 */
	void getAllModules() {
		
		File directory = new File("");// 参数为空
		String courseFile = "";
        try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
         
		String path = this.getClass().getResource("").toString();
		path = path.replace("file:/", "/").replace("core/", "")
				.replace("build/classes", "src");

       String path2 = courseFile+FILE_SEP  +"src";
        
		File root = new File(path);
		File[] modules = root.listFiles();
		for (File childFile : modules) {
			if (childFile.isDirectory()) {
				File domainDir = new File(childFile + FILE_SEP + "domain");
				if (domainDir.isDirectory() && domainDir.exists()) {

					moduleMap.put(childFile.getName(), childFile.getPath());
				}
			}
		}

		printDir();

	}

	void genFile() {
		try {

			for (String key : moduleMap.keySet()) {

				File domainDir = new File(moduleMap.get(key) + FILE_SEP
						+ DOMAIN);
				File[] files = domainDir.listFiles();

				for (File file : files) {

					saveService(file, key);
					saveDao(file, key);
					saveImpl(file, key);
					saveController(file, key);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 创建各层目录
	 */
	void genDir() {
		try {

			for (String key : moduleMap.keySet()) {

				// service
				File fileService = new File(moduleMap.get(key) + FILE_SEP
						+ SERVICE);
				if (!fileService.exists()) {
					fileService.mkdir();
				}

				// dao
				File fileDao = new File(moduleMap.get(key) + FILE_SEP + DAO);
				if (!fileDao.exists()) {
					fileDao.mkdir();
				}

				// impl
				File fileImpl = new File(moduleMap.get(key) + FILE_SEP + DAO
						+ FILE_SEP + IMPL);
				if (!fileImpl.exists()) {
					fileImpl.mkdir();
				}

				// controller
				File fileController = new File(moduleMap.get(key) + FILE_SEP
						+ CONTROLLER);
				if (!fileController.exists()) {
					fileController.mkdir();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 生成文件
	 * 
	 * @param file
	 * @param key
	 */
	void saveService(File file, String key) {

		try {
			String root = moduleMap.get(key) + FILE_SEP + SERVICE + FILE_SEP
					+ file.getName().replace(EXT, "") + "Service" + EXT;
			File f = new File(root);
			if (!f.exists()) {
				f.createNewFile();

				String s = getWriteService(key, file.getName().replace(EXT, ""));
				FileOutputStream out = new FileOutputStream(f);
				out.write(s.getBytes());
				out.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void saveDao(File file, String key) {
		try {
			String root = moduleMap.get(key) + FILE_SEP + DAO + FILE_SEP + "I"
					+ file.getName().replace(EXT, "") + "Dao" + EXT;
			File f = new File(root);
			if (!f.exists()) {
				f.createNewFile();

				String s = getWriteDao(key, file.getName().replace(EXT, ""));
				FileOutputStream out = new FileOutputStream(f);
				out.write(s.getBytes());
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void saveImpl(File file, String key) {
		try {
			String root = moduleMap.get(key) + FILE_SEP + DAO + FILE_SEP + IMPL
					+ FILE_SEP + file.getName().replace(EXT, "") + "Dao" + EXT;
			File f = new File(root);
			if (!f.exists()) {
				f.createNewFile();

				String s = getWriteImpl(key, file.getName().replace(EXT, ""));
				FileOutputStream out = new FileOutputStream(f);
				out.write(s.getBytes());
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void saveController(File file, String key) {
		try {
			String root = moduleMap.get(key) + FILE_SEP + CONTROLLER + FILE_SEP
					+ file.getName().replace(EXT, "") + "Controller" + EXT;
			File f = new File(root);
			if (!f.exists()) {
				f.createNewFile();
				
				String s = getWriteController(key, file.getName().replace(EXT, ""));
				FileOutputStream out = new FileOutputStream(f);
				out.write(s.getBytes());
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印模块
	 */
	void printDir() {
		for (String key : moduleMap.keySet()) {
			System.out.println(key + "-" + moduleMap.get(key));
		}

	}

	String getWriteDao(String layer, String entity) {
		StringBuilder sb = new StringBuilder();

		sb.append("package com.xiaba2." + layer + ".dao;");
		sb.append(LINE);
		sb.append("import java.util.UUID;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".domain." + entity + ";");
		sb.append(LINE);
		sb.append("import com.xiaba2.core.IBaseDao;");
		sb.append(LINE);
		sb.append("public interface I" + entity + "Dao extends IBaseDao<"
				+ entity + ", UUID> {");
		sb.append(LINE);
		sb.append("}");

		return sb.toString();

	}

	String getWriteImpl(String layer, String entity) {

		StringBuilder sb = new StringBuilder();

		sb.append("package com.xiaba2." + layer + ".dao.impl;");
		sb.append(LINE);
		sb.append("import java.util.UUID;");
		sb.append(LINE);
		sb.append("import org.springframework.stereotype.Repository;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".dao.I" + entity + "Dao;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".domain." + entity + ";");
		sb.append(LINE);
		sb.append("import com.xiaba2.core.AbstractHibernateDao;");
		sb.append(LINE);
		sb.append("@Repository");
		sb.append(LINE);
		sb.append("public class " + entity
				+ "Dao extends AbstractHibernateDao<" + entity
				+ ", UUID> implements");
		sb.append(LINE);
		sb.append("I" + entity + "Dao {");
		sb.append(LINE);
		sb.append("}");
		return sb.toString();
	}

	String getWriteService(String layer, String entity) {

		StringBuilder sb = new StringBuilder();
		sb.append("package com.xiaba2." + layer + ".service;");
		sb.append(LINE);
		sb.append("import java.util.UUID;");
		sb.append(LINE);
		sb.append("import javax.annotation.Resource;");
		sb.append(LINE);
		sb.append("import org.springframework.stereotype.Service;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".dao.I" + entity + "Dao;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".domain." + entity + ";");
		sb.append(LINE);
		sb.append("import com.xiaba2.core.BaseService;");
		sb.append(LINE);
		sb.append("import com.xiaba2.core.IBaseDao;");
		sb.append(LINE);
		sb.append("@Service");
		sb.append(LINE);
		sb.append("public class " + entity + "Service extends BaseService<"
				+ entity + ", UUID> {");
		sb.append(LINE);
		sb.append("@Resource");
		sb.append(LINE);
		sb.append("private I" + entity + "Dao "
				+ entity.substring(0, 1).toLowerCase()
				+ entity.substring(1, entity.length()) + "Dao;");
		sb.append(LINE);
		sb.append("@Override");
		sb.append(LINE);
		sb.append("protected IBaseDao<" + entity + ", UUID> getEntityDao() {");
		sb.append(LINE);
		sb.append("return " + entity.substring(0, 1).toLowerCase()
				+ entity.substring(1, entity.length()) + "Dao;");
		sb.append(LINE);
		sb.append("}");
		sb.append(LINE);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * controller
	 * @return
	 */
	String getWriteController(String layer, String entity) {
		StringBuilder sb = new StringBuilder();
		sb.append("package com.xiaba2." + layer + ".controller;");
		sb.append(LINE);
		sb.append("import java.util.List;");
		sb.append(LINE);
		sb.append("import javax.annotation.Resource;");
		sb.append(LINE);
		sb.append("import org.springframework.stereotype.Controller;");
		sb.append(LINE);
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;");
		sb.append(LINE);
		sb.append("import org.springframework.web.servlet.ModelAndView;");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".domain." + entity + ";");
		sb.append(LINE);
		sb.append("import com.xiaba2." + layer + ".service." + entity + "Service;");
		sb.append(LINE);
		sb.append("@Controller");
		sb.append(LINE);
		sb.append("@RequestMapping(\"/" + entity.toLowerCase() + "\")");
		sb.append(LINE);
		sb.append("public class " + entity + "Controller {");
		sb.append(LINE);
		sb.append("@Resource");
		sb.append(LINE);
		sb.append("private " + entity + "Service " + entity.substring(0, 1).toLowerCase()
				+ entity.substring(1, entity.length()) + "Service;");
		sb.append(LINE);
		sb.append("}");

		return sb.toString();
	}

}
