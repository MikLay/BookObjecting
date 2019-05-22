package com.company.utils;

import com.company.exception.ReadException;

public interface Readable {
    String toRead() throws ReadException;
}
