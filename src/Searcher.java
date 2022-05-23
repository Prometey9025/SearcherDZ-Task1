import java.io.*;
import java.util.ArrayList;


public class Searcher {
    private String directory;
    private ArrayList<File> filesList = new ArrayList<>();
    private String resultPath;


    public Searcher(String directory) {
        this.directory = directory;
        this.resultPath = directory + "\\result.txt";
    }

    public static void main(String[] args) {


    }


    //ввод директории с консоли и её проверка на валидность
    public static String input(){
        String directory = null;
        try {
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите директорию для поиска:");
            directory = bufferedInputStream.readLine().trim();
            if(directory.equals("exit"))  System.exit(0);

            File file = new File(directory);
            if(!file.exists()) throw new FileNotFoundException();

        }catch (Exception ex){

            if(ex.getClass().getSimpleName().equals("FileNotFoundException")) {
                System.out.println("Некорректная директория, пожалуйста " +
                        "проверьте данные. Для завершения работы программы введите: exit");
                return input();
            }
            else ex.printStackTrace();
        }

        return directory;
    }

    //рекурсивный метод для поиска файлов в директории, заполняет filesList
    public void search(File directory){
        if(directory.isDirectory()){
            for(File file : directory.listFiles()){
                if(file.isFile() && (file.getName().endsWith(".txt") ||  file.getName().endsWith(".TXT"))){
                    filesList.add(file);
                } else {
                    search(file);
                }
            }
        }
        if(filesList.size() == 0){
            System.out.println("В указанной директории файлов формата \".txt\" нет, " +
                    "в итоговом фале нет новых записей.");
            System.exit(0);
        }

    }

}
