package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 该类提供ZIP格式文件压缩，文件夹压缩功能，以及压缩包解压功能，
 * 
 * @author wangliuyang
 *
 */
public class ZipUtil {
	private ZipUtil() throws Exception {
		throw new Exception("util shoud not be initialized!!");
	}
	/**
	 * 压缩给定目录 ，支持文件压缩 ，文件夹压缩，多级目录压缩
	 * 
	 * @param toBeZipPath
	 *            被压缩的路径，可以是文件夹也可以是文件
	 * @param zipToPath
	 *            压缩到目标路径
	 * @param zipToFileName
	 *            压缩后的文件名
	 * @return 压缩后目标文件
	 * 
	 */
	public static final File zip(String toBeZipPath, String zipToPath,
			String zipToFileName) throws IOException {
		File srcFile = new File(toBeZipPath);
		if (!srcFile.exists()) {
			return null;
		}
		File destF = new File(zipToFileName);
		if (!destF.exists()) {
			destF.mkdirs();
		}
		File desFile = new File(destF, zipToFileName);
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(desFile));
		if (srcFile.isDirectory()) {
			zipDir(srcFile, zos, "");
		} else {
			zipFile(srcFile, zos, "");
		}
		zos.close();
		return desFile;

	}

	/**
	 * 压缩单个文件，不会关闭传入的压缩输出流
	 * 
	 * @param srcFile
	 *            被压缩的文件
	 * @param zos
	 *            压缩流
	 * @param parent
	 *            该压缩文件在压缩包中的父目录（并非系统父目录），可以为空,如果为空时不要传null 用""代替
	 * @throws IOException
	 *             抛出文件读写异常
	 */
	private static void zipFile(File srcFile, ZipOutputStream zos, String parent)
			throws IOException {
		if (!srcFile.exists()) {
			return;
		}
		System.out.println(parent);
		InputStream fis = new FileInputStream(srcFile);
		zos.putNextEntry(new ZipEntry(parent + srcFile.getName()));
		int readCount = 0;
		while ((readCount = fis.read()) != -1) {
			zos.write(readCount);
		}
		fis.close();
	}

	/**
	 * 压缩文件夹里面的文件和子文件夹，跳过空文件夹，不会关闭传入的压缩输出流
	 * 
	 * @param srcFile
	 *            压缩文件夹
	 * @param zos
	 *            压缩流
	 * @param parent
	 *            在压缩包中的父目录（并非系统父目录）,可以为空 ，如果为空时不要传null 用""代替
	 * @throws IOException
	 *             读写异常
	 */
	private static void zipDir(File srcFile, ZipOutputStream zos, String parent)
			throws IOException {
		File[] files = srcFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				zipDir(file, zos, parent + file.getName() + File.separator);
			} else {
				zipFile(file, zos, parent);
			}
		}
	}

	/**
	 * 解压缩包
	 * 
	 * @param srcFile
	 *            压缩包路径
	 * 
	 * @param destFileName
	 *            解压缩后文件名
	 * @return 解压后文件
	 * 
	 * @throws IOException
	 */
	public static final File unZip(String srcFile, String destFile)
			throws IOException {
		File srcF = new File(srcFile);
		if (!srcF.exists()) {
			return null;
		}
		File f = new File(destFile);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(srcF));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			File destF = new File(f, entry.getName());
			File parent = destF.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(destF);
			int readCount;
			while ((readCount = zis.read()) != -1) {
				fos.write(readCount);
			}
			fos.close();
		}
		zis.close();
		return f;
	}
	
}
