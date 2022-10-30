package com.shindhe.listener;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import com.shindhe.model.StudentCsv;
import com.shindhe.model.StudentJson;

@Component
public class SkipListenerImpl implements SkipListener<StudentCsv, StudentJson> {

	@Override
	public void onSkipInRead(Throwable th) {
		if(th instanceof FlatFileParseException) {
			createFile("C:\\chunk_job\\chunk_step\\reader\\SkipInRead.txt",
					((FlatFileParseException) th).getInput());
		}
	}

	@Override
	public void onSkipInWrite(StudentJson item, Throwable t) {
		createFile("C:\\chunk_job\\chunk_step\\writer\\SkipInWrite.txt",
				item.toString());
	}

	@Override
	public void onSkipInProcess(StudentCsv item, Throwable t) {
		createFile("C:\\chunk_job\\chunk_step\\processor\\SkipInProcess.txt",
				item.toString());
	}
	
	public void createFile(String filePath, String data) {
		try(FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
			fileWriter.write(data + "," + new Date() + "\n");
		}catch(Exception e) {
			
		}
	}

}
