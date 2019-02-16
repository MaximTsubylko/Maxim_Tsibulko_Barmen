package com.tsibulko.finaltask.util.TestUtil.parser;

import java.io.FileNotFoundException;
import java.util.List;
@FunctionalInterface
public interface Parser<T> {
   List<T> parse(String path);
}
