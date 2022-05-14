package com.keepbook.app.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.keepbook.app.model.dto.KeepBookDTO;

public class BookUtils {

    private final SqlLiteUtils sqlLiteUtils;
    private final SQLiteDatabase writableDatabase;

    public BookUtils(SqlLiteUtils sqlLiteUtils) {
        this.sqlLiteUtils = sqlLiteUtils;
        writableDatabase = sqlLiteUtils.getWritableDatabase();
    }

    public Long writeDataOfOne(KeepBookDTO bookDTO) {
        ContentValues values = new ContentValues();
        values.put("category", bookDTO.getCategory());
        values.put("remark", bookDTO.getRemark());
        values.put("money", bookDTO.getMoney());
        return writableDatabase.insert(
                "keepbook", null, values
        );
    }
}
