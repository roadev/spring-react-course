package dev.juanroa.sprintreactnew.services;

import dev.juanroa.sprintreactnew.data.entities.VendorEntity;
import dev.juanroa.sprintreactnew.data.repositories.VendorRepository;
import dev.juanroa.sprintreactnew.web.errors.NotFoundException;
import dev.juanroa.sprintreactnew.web.models.Vendor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

  private final VendorRepository vendorRepository;

  public VendorService(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  public List<Vendor> getVendors(){
    Iterable<VendorEntity> vendorEntities = this.vendorRepository.findAll();
    List<Vendor> vendors = new ArrayList<>();
    vendorEntities.forEach(vendorEntity -> {
      vendors.add(this.translateDbToWeb(vendorEntity));
    });
    return vendors;
  }

  public Vendor getVendor(long id){
    Optional<VendorEntity> optional = this.vendorRepository.findById(id);
    if (optional.isEmpty()){
      throw new NotFoundException("vendor entity not found with id: " + id);
    }
    return this.translateDbToWeb(optional.get());
  }

  public Vendor createOrUpdateVendor(Vendor vendor){
    VendorEntity entity = this.translateWebToDb(vendor);
    entity = this.vendorRepository.save(entity);
    return this.translateDbToWeb(entity);
  }

  public void deleteVendor(long id){
    this.vendorRepository.deleteById(id);
  }

  private Vendor translateDbToWeb(VendorEntity entity){
    return new Vendor(
        entity.getId(),
        entity.getName(),
        entity.getContact(),
        entity.getEmail(),
        entity.getPhone(),
        entity.getAddress()
    );
  }

  private VendorEntity translateWebToDb(Vendor vendor){
    VendorEntity entity = new VendorEntity();
    entity.setId(vendor.getVendorId()==null?0:vendor.getVendorId());
    entity.setAddress(vendor.getAddress());
    entity.setContact(vendor.getContact());
    entity.setEmail(vendor.getEmailAddress());
    entity.setName(vendor.getName());
    entity.setPhone(vendor.getPhoneNumber());
    return entity;
  }
}
