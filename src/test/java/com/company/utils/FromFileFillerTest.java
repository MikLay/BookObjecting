package com.company.utils;

import com.company.exception.ReadException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class FromFileFillerTest {

    @BeforeClass
    public static void createFile() {
        File directory = new File(".");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(directory.getCanonicalPath() + File.separator + "src\\test\\java\\com\\company\\utils\\" + "TestingFile.txt"), StandardCharsets.UTF_8))) {
            writer.write("Интересы авторов, направленные на обучение, определили структуру этой\n" +
                    "книги. Книга предназначена как для начинающих изучение Java-технологий,\n" +
                    "так и для студентов и программистов, переходящих на Java с другого языка\n" +
                    "программирования. Авторы считают, что «профессионала под ключ» обучить\n" +
                    "нельзя, им становятся только после участия в разработке нескольких серьезных Java-проектов.\n" +
                    "В то же время данный курс может служить ступенькой к мастерству. Прошедшие обучение по этому курсу успешно сдают различные\n" +
                    "экзамены, получают международные сертификаты и в состоянии участвовать\n" +
                    "в командной разработке промышленных программных .gitignore проектов проектов? Привет!\n");
        } catch (IOException ignored) {
        }

    }

    @AfterClass
    public static void deleteFile() {

        try {
            Files.deleteIfExists(Paths
                    .get(new File(".")
                            .getCanonicalPath() +
                            File.separator + "src\\test\\java\\com\\company\\utils\\"
                            + "TestingFile.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ShouldReadFile() throws ReadException {
        String expected = "Интересы авторов, направленные на обучение, определили структуру этой\n" +
                "книги. Книга предназначена как для начинающих изучение Java-технологий,\n" +
                "так и для студентов и программистов, переходящих на Java с другого языка\n" +
                "программирования. Авторы считают, что «профессионала под ключ» обучить\n" +
                "нельзя, им становятся только после участия в разработке нескольких серьезных Java-проектов.\n" +
                "В то же время данный курс может служить ступенькой к мастерству. Прошедшие обучение по этому курсу успешно сдают различные\n" +
                "экзамены, получают международные сертификаты и в состоянии участвовать\n" +
                "в командной разработке промышленных программных .gitignore проектов проектов? Привет!\n";

        FromFileFiller fromFileFiller;
        try {
            fromFileFiller = new FromFileFiller(new File(".").getCanonicalPath() + File.separator + "src\\test\\java\\com\\company\\utils\\" + "TestingFile.txt");
        } catch (IOException e) {
            throw new ReadException();
        }
        String actual = fromFileFiller.toRead();
        assertEquals(expected, actual);
    }
}