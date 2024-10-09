package com.learning.post.service.impl;

import com.learning.post.dto.UserDto;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CSVServiceImpl {

    public void writeUsersToCsv(PrintWriter printWriter, List<UserDto> userDtoList) throws IOException {
        CSVWriter csvWriter = new CSVWriter(printWriter);
        String[] header = {"Id", "Name", "Email", "Joined On"};
        csvWriter.writeNext(header);

        for (UserDto userDto : userDtoList) {
            String[] data = {String.valueOf(userDto.getId()), userDto.getName(), userDto.getEmail(), String.valueOf(userDto.getJoinedOn())};

            csvWriter.writeNext(data);
        }
        csvWriter.close();
    }
}
