package de.malikatalla.ling.preparation;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

  public static File findWikiDump() throws IOException{
    return FileUtils.findFile(DBCreator.WIKI_DUMP_DIR, DBCreator.WIKI_DUMP_PATTERN);
  }
  
  public static File findFile(String dir, final String pattern) throws IOException{
    FileFinder fileFinder = new FileFinder(pattern);
    Files.walkFileTree(Paths.get(dir),fileFinder);
    return fileFinder.getFoundFile();
  }
  
  private static class FileFinder extends SimpleFileVisitor<Path> {
    
    private String pattern;
    private File foundFile = null;
    public FileFinder(String pattern){
      this.pattern = pattern;
    }
    
    public File getFoundFile() {
      return foundFile;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
      String fileName = file.getFileName().toString();
      if (fileName.matches(pattern)){
        foundFile = new File(file.toString());
      }
      return super.visitFile(file, attrs);
    }
  }
}
