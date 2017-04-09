package com.company;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.*;

/**
 * Created by admin on 09.04.2017.
 */
public class WordSet {
    public CopyOnWriteArraySet<String> wordSet = new CopyOnWriteArraySet<>();
    public AtomicBoolean flStop = new AtomicBoolean(false);
}
