/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.batch;

import java.util.function.Predicate;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

/**
 *
 * @author sanayapc
 */
public class FilterItemReaderAdapter<T> implements ItemStreamReader<T>{
    private final ItemStreamReader<T> reader;

    private final Predicate<T> filterCondition;

    /**
     * @param reader          the {@link ItemStreamReader} implementation to delegate to
     * @param filterCondition Condition predicate.
     */
    public FilterItemReaderAdapter(final ItemStreamReader<T> reader, final Predicate<T> filterCondition) {
        this.reader = reader;
        this.filterCondition = filterCondition;
    }

    @Override
    public T read() throws Exception {
        T item;
        do {
            item = reader.read();
        } while (item != null && !filterCondition.test(item));
        return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        reader.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        reader.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        reader.close();
    }
}
