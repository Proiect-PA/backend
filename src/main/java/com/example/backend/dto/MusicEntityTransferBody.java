package com.example.backend.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class MusicEntityTransferBody<E> {
    protected UUID userId;
    protected E entity;
}
