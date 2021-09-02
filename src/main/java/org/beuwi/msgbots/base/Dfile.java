package org.beuwi.msgbots.base;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.utils.SharedValues;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// [Data File] : 특수하게 관리해야 하므로 따로 클래스 작성
public class Dfile {
    String name;
    public Dfile(String name) {
        this.name = name;
    }

    public boolean create() {
        try {
            return toFile().createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 유저가 값을 변경하면 파일이 생성되므로, 생성된 적이 없다면 기본 파일만 있음
    public boolean isCreated() {
        return toFile().exists();
    }

    // 프로그램 밖 (유저 파일) : [Write, Read]
    public File toFile() {
        File folder = SharedValues.getFile("file.dataFolder");
        return new File( folder + File.separator + name);
    }
    // 프로그램 안 (기본 파일) : [Read Only]
    public InputStream toResource() {
        return ResourceUtils.getData(name);
    }

    public String getData() {
        File file = toFile();
        String result;

        // 유저가 파일을 변경한 적이 있다면 해당 파일이 생성 돼 있으므로
        if (file.exists()) {
            result = FileManager.read(file);
        }
        // 아니라면 기본 값을 위해 리소스 파일을 반환
        else {
            result = FileManager.read(ResourceUtils.getData(name));
        }

        return result;
    }
    
    // 저장의 경우 무조건 파일 생성
    public void setData(String data) {
        FileManager.write(toFile(), data);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
