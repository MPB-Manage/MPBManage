package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.ReservationSettingRequest;
import dk.mpb.manage.project.dto.ReservationSettingResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.ReservationSetting;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.ReservationSettingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationSettingService {
    private PropertyRepository propertyRepository;
    private ReservationSettingRepository reservationSettingRepository;

    public ReservationSettingService(PropertyRepository propertyRepository, ReservationSettingRepository reservationSettingRepository) {
        this.propertyRepository = propertyRepository;
        this.reservationSettingRepository = reservationSettingRepository;
    }

    public ReservationSettingResponse updateReservationSetting(String name, ReservationSettingRequest reservationSettingRequest) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        ReservationSetting reservationSetting = reservationSettingRepository.findByPropertyId(property.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation setting not found"));
        if(reservationSetting.getStandardPrice() != reservationSettingRequest.getStandardPrice()) {
            reservationSetting.setStandardPrice(reservationSettingRequest.getStandardPrice());
        } else if(reservationSetting.getJanuaryPrice() != reservationSettingRequest.getJanuaryPrice()) {
            reservationSetting.setJanuaryPrice(reservationSettingRequest.getJanuaryPrice());
        } else if(reservationSetting.getFebruaryPrice() != reservationSettingRequest.getFebruaryPrice()) {
            reservationSetting.setFebruaryPrice(reservationSettingRequest.getFebruaryPrice());
        } else if(reservationSetting.getMarchPrice() != reservationSettingRequest.getMarchPrice()) {
            reservationSetting.setMarchPrice(reservationSettingRequest.getMarchPrice());
        } else if(reservationSetting.getAprilPrice() != reservationSettingRequest.getMarchPrice()) {
            reservationSetting.setAprilPrice(reservationSettingRequest.getAprilPrice());
        } else if(reservationSetting.getMayPrice() != reservationSettingRequest.getMayPrice()) {
            reservationSetting.setMayPrice(reservationSettingRequest.getMayPrice());
        } else if(reservationSetting.getJunePrice() != reservationSettingRequest.getJunePrice()) {
            reservationSetting.setJunePrice(reservationSettingRequest.getJunePrice());
        } else if(reservationSetting.getJulyPrice() != reservationSettingRequest.getJulyPrice()) {
            reservationSetting.setJulyPrice(reservationSettingRequest.getJulyPrice());
        } else if(reservationSetting.getAugustPrice() != reservationSettingRequest.getAugustPrice()) {
            reservationSetting.setAugustPrice(reservationSettingRequest.getAugustPrice());
        } else if(reservationSetting.getSeptemberPrice() != reservationSettingRequest.getSeptemberPrice()) {
            reservationSetting.setSeptemberPrice(reservationSettingRequest.getSeptemberPrice());
        } else if(reservationSetting.getOctoberPrice() != reservationSettingRequest.getOctoberPrice()) {
            reservationSetting.setOctoberPrice(reservationSettingRequest.getOctoberPrice());
        } else if(reservationSetting.getNovemberPrice() != reservationSettingRequest.getNovemberPrice()) {
            reservationSetting.setNovemberPrice(reservationSettingRequest.getNovemberPrice());
        } else if(reservationSetting.getDecemberPrice() != reservationSettingRequest.getDecemberPrice()) {
            reservationSetting.setDecemberPrice(reservationSettingRequest.getDecemberPrice());
        }
        ReservationSetting reservationSettingResponse = reservationSettingRepository.save(reservationSetting);
        return new ReservationSettingResponse(reservationSettingResponse);
    }
}
