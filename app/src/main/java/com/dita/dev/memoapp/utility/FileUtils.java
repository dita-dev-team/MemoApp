package com.dita.dev.memoapp.utility;


import android.webkit.MimeTypeMap;

import com.dita.dev.memoapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FileUtils {
    public static final String[] videoTypes = {
            "video/mp4",
            "video/m4v"
    };

    public static final String[] audioTypes = {
            "audio/mpeg"
    };

    public static final String[] wordTypes = {
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/msword"
    };

    public static final Map<String, Integer> mimeTypes = new HashMap<>();

    static {
        mimeTypes.put("audio", R.drawable.file_audio);
        mimeTypes.put("video", R.drawable.file_video);
        mimeTypes.put("pdf", R.drawable.file_pdf);
        mimeTypes.put("word", R.drawable.file_word);
        mimeTypes.put("generic", R.drawable.file_general);
    }

    public static String getExtension(String filename) {
        String extension = null;
        extension = MimeTypeMap.getFileExtensionFromUrl(filename);
        return extension;
    }

    public static String getFileType(String filename) {
        String fileType = null;
        String extension = getExtension(filename);

        if (extension != null) {
            fileType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }

        return fileType;
    }

    public static String getGenericFileType(String fileType) {
        String genericFileType = null;
        if (Arrays.asList(videoTypes).contains(fileType)) {
            genericFileType = "video";
        } else if (Arrays.asList(audioTypes).contains(fileType)) {
            genericFileType = "audio";
        } else if (Arrays.asList(wordTypes).contains(fileType)) {
            genericFileType = "word";
        } else if (fileType.contains("pdf")) {
            genericFileType = "pdf";
        } else {
            genericFileType = "generic";
        }

        return genericFileType;
    }

    public static String getLastModifiedDate(File file) {
        String formattedDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy hh:mm a");
        formattedDate = format.format(new Date(file.lastModified()));
        return formattedDate;
    }

    public static String getUniqueFilename(String extension) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date now = new Date();
        String fileName = formatter.format(now) + extension;
        return fileName;
    }

    public static String removeWhitespace(String filename) {
        return filename.replace(" ", "_");
    }
}
