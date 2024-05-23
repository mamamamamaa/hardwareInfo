package com.hardwareInfo.hardwareInfo.services;

import com.hardwareInfo.hardwareInfo.entities.GpuModelEntity;
import com.hardwareInfo.hardwareInfo.repositories.GpuModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GpuModelService {
    private final GpuModelRepository gpuModelRepository;

    public GpuModelEntity createNewEntity(GpuModelEntity gpuModel){
        return gpuModelRepository.save(gpuModel);
    }

    @DeleteMapping
    public void deleteGpuModel(Long id){
        gpuModelRepository.deleteById(id);
    }
    @PutMapping
    public Optional<GpuModelEntity> updateGpuModel(GpuModelEntity newGpuModel, Long id){

        return gpuModelRepository.findById(id).map(gpuModel ->{
           gpuModel.setGpuVendor(newGpuModel.getGpuVendor());
           gpuModel.setGpuRev(newGpuModel.getGpuRev());
           gpuModel.setGpuName(newGpuModel.getGpuName());
           gpuModel.setGraphicsCards(newGpuModel.getGraphicsCards());
           gpuModel.setRop(newGpuModel.getRop());
           gpuModel.setTmu(newGpuModel.getTmu());
           gpuModel.setShaderProcessors(newGpuModel.getShaderProcessors());
           gpuModel.setArchitecture(newGpuModel.getArchitecture());
           gpuModel.setDieSize(newGpuModel.getDieSize());
           gpuModel.setGraphicsCards(newGpuModel.getGraphicsCards());
           return gpuModel;
        });
    }


    public List<GpuModelEntity> getAllGpuModels(){

        return gpuModelRepository.findAll();
    }


    public Optional<GpuModelEntity> getGpuModelById(Long id){


        return gpuModelRepository.findById(id);
    }

}
