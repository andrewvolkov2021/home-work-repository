package by.it_academy.jd2.Mk_JD2_82_21_chat.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_chat.service.FileService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.StorageService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.UserFileService;
import by.it_academy.jd2.Mk_JD2_82_21_chat.service.UserStorageService;

public enum EStorageType {
    MEMORY_OF_APP(StorageService.getInstance(), UserStorageService.getInstance()),
    FILE(FileService.getInstance(), UserFileService.getInstance());

    private final IHandleStorage handler;
    private final IHandleInfo handleInfo;

    EStorageType(IHandleStorage handler, IHandleInfo handleInfo) {
        this.handler = handler;
        this.handleInfo = handleInfo;
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

    public IHandleInfo getHandleInfo(){
        return this.handleInfo;
    }
}
