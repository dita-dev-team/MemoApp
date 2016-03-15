package com.dita.dev.memoapp.utility;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValid(EditText editText, boolean noSpace, boolean required) {
        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (required && text.isEmpty()) {
            editText.setError("Required");
            return false;
        }

        if (noSpace && containsWhiteSpace(text)) {
            editText.setError("Invalid input");
            return false;
        }

        return true;
    }

    public static boolean containsWhiteSpace(final String text) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
