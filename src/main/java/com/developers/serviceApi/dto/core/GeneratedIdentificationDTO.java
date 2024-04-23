package com.developers.serviceApi.dto.core;


import com.developers.serviceApi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedIdentificationDTO implements SuperDTO {
    long id;
    String prefix;
}
