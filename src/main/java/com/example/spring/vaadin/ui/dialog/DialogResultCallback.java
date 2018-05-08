package com.example.spring.vaadin.ui.dialog;

public interface DialogResultCallback<T> {
    public void resultOk(T result);
    public void resultCancel();
}
