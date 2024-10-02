package com.api4.service;

import com.api4.entity.Registration;
import com.api4.exception.ResourceNotFoundException;
import com.api4.payload.RegistrationDto;
import com.api4.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository rp;
    private ModelMapper mp;


    public RegistrationService(RegistrationRepository rp, ModelMapper mp) {
        this.rp = rp;

        this.mp = mp;
    }
    public List <RegistrationDto> getRegistrations(){
        List <Registration> jj = rp.findAll();
       List <RegistrationDto> dto = jj.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }
    public RegistrationDto createReg(RegistrationDto dto){
        Registration registration = mapToEntity(dto);
      Registration fg =  rp.save(registration);
RegistrationDto ref = mapToDto(fg);


      return ref;
    }
    public void deleteReg(long id){
        rp.deleteById(id);
    }
    public Registration updateReg(long id , Registration registration){
        Registration r = rp.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
       Registration savedEntity = rp.save(r);
       return savedEntity;
    }
   Registration  mapToEntity(RegistrationDto dto){
       Registration registration = mp.map(dto , Registration.class);
        //Registration registration = new Registration();
        //registration.setName(dto.getName());
        //registration.setEmail(dto.getEmail());
        //registration.setMobile(dto.getMobile());
        return registration ;

    }
    RegistrationDto mapToDto(Registration registration){
       RegistrationDto dto = mp.map(registration , RegistrationDto.class);
    // RegistrationDto dto = new RegistrationDto();
    // dto.setName(registration.getName());
    // dto.setEmail(registration.getEmail());
    // dto.setMobile(registration.getMobile());
     return dto;
    }

    public RegistrationDto getRegById(long id) {
       Registration reg = rp.findById(id).orElseThrow(
               ()->new ResourceNotFoundException("Record not Found")
       );
       return mapToDto(reg);
    }
}
