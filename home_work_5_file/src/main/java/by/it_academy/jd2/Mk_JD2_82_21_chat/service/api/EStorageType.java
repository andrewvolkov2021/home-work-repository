package by.it_academy.jd2.Mk_JD2_82_21_chat.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.FileService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;

public enum EStorageType {
    MEMORY_OF_APP(StorageService.getInstance()),
    FILE(FileService.getInstance());

    private final IHandleStorage handler;

    EStorageType(IHandleStorage handler) {
        this.handler = handler;
    }

    public static EStorageType valueOfIgnoreCase(String name) {
        for (EStorageType value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }

    public IHandleStorage getHandler(){
        return this.handler;
    }
}
