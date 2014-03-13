package de.malikatalla.ling.ling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Danny Remington - MacroSolve
 * 
 *         Helper class for common tasks using files.
 * 
 */
public class FileHelper {
  /**
   * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
   * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then it
   * will be replaced with a copy of <i><b>fromFile</b></i>. The name and path
   * of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
   * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
   * operation.
   * 
   * @param fromFile
   *          - InputStream for the file to copy from.
   * @param toFile
   *          - InputStream for the file to copy to.
   */
  public static void copyFile(InputStream fromFile, OutputStream toFile) throws IOException {
    // transfer bytes from the inputfile to the outputfile
    byte[] buffer = new byte[1024];
    int length;

    try {
      while ((length = fromFile.read(buffer)) > 0) {
        toFile.write(buffer, 0, length);
      }
    }
    // Close the streams
    finally {
      try {
        if (toFile != null) {
          try {
            toFile.flush();
          } finally {
            toFile.close();
          }
        }
      } finally {
        if (fromFile != null) {
          fromFile.close();
        }
      }
    }
  }

  /**
   * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
   * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then it
   * will be replaced with a copy of <i><b>fromFile</b></i>. The name and path
   * of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
   * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
   * operation.
   * 
   * @param fromFile
   *          - String specifying the path of the file to copy from.
   * @param toFile
   *          - String specifying the path of the file to copy to.
   */
  public static void copyFile(String fromFile, String toFile) throws IOException {
    copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
  }

  /**
   * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
   * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then it
   * will be replaced with a copy of <i><b>fromFile</b></i>. The name and path
   * of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
   * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
   * operation.
   * 
   * @param fromFile
   *          - File for the file to copy from.
   * @param toFile
   *          - File for the file to copy to.
   */
  public static void copyFile(File fromFile, File toFile) throws IOException {
    copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
  }
}
