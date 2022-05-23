import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Searcher {
    private String directory;
    private ArrayList<File> filesList = new ArrayList<>();
    private String resultPath;


    public Searcher(String directory) {
        this.directory = directory;
        this.resultPath = directory + "\\result.txt";
    }

    public static void main(String[] args) {
        String directory  = input();

        Searcher searcher = new Searcher(directory);

        File file = new File(searcher.directory);
        searcher.search(file);

        searcher.sort();

        searcher.write();

        System.out.println(String.format("Всего найдено txt файлов : %d, данные из которых были записаны " +
                "в итоговый файл: %s", searcher.filesList.size(),searcher.resultPath));

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

    //метод для сортировки файлов по названию
    public void sort(){

        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(this.filesList, comparator);

    }

    //метод для записи содержимого файлов в итоговый файл
    public void write() {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.resultPath));
            for ( File file : this.filesList ) {
                try(BufferedReader br = new BufferedReader(new FileReader(file.toString())))
                {
                    while (br.ready()){
                        bw.write(br.readLine());
                        bw.newLine();
                    }
                }
            }
            bw.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



}
