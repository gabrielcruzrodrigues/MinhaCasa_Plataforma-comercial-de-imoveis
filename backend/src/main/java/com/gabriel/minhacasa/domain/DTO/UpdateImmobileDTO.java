package com.gabriel.minhacasa.domain.DTO;

import com.gabriel.minhacasa.domain.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateImmobileDTO {
        @NotNull(message = "immobileId is required")
        private Long immobileId;

        @NotNull(message = "files is required")
        private List<MultipartFile> files;

        @NotBlank(message = "Title is required")
        private String immobileTitle;

        @NotBlank(message = "Description is required")
        private String description;

        @NotBlank(message = "Address is required")
        private String address;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "Neighborhood is required")
        private String neighborhood;

        @NotBlank(message = "State is required")
        private String state;

        @NotNull(message = "Garage information is required")
        private boolean garage;

        @NotNull(message = "Quantity of bedrooms is required")
        private int quantityBedrooms;

        @NotNull(message = "Quantity of rooms is required")
        private int quantityRooms;

        @NotNull(message = "IPTU is required")
        private BigDecimal IPTU;

        @NotNull(message = "Price is required")
        private BigDecimal price;

        @NotNull(message = "Suite information is required")
        private boolean suite;

        @NotNull(message = "Total area is required")
        private Double totalArea;

        @NotNull(message = "Quantity of bathrooms is required")
        private int quantityBathrooms;

        @NotNull(message = "Integrity is required")
        private IntegrityEnum integrity;

        @NotNull(message = "Seller type is required")
        private SellerTypeEnum sellerType;

        @NotNull(message = "Age is required")
        private AgeEnum age;

        @NotNull(message = "Category is required")
        private CategoryEnum category;

        @NotNull(message = "Type is required")
        private TypeEnum type;

        @NotNull(message = "Garden information is required")
        private boolean garden;

        @NotNull(message = "Beach information is required")
        private boolean beach;

        @NotNull(message = "Disabled access information is required")
        private boolean disabledAccess;

        @NotNull(message = "Playground information is required")
        private boolean playground;

        @NotNull(message = "Grill information is required")
        private boolean grill;

        @NotNull(message = "Energy generator information is required")
        private boolean energyGenerator;

        @NotNull(message = "Close to the center information is required")
        private boolean closeToTheCenter;

        @NotNull(message = "Elevator information is required")
        private boolean elevator;

        @NotNull(message = "Pool information is required")
        private boolean pool;

        @NotNull(message = "Front desk information is required")
        private boolean frontDesk;

        @NotNull(message = "Multi sports court information is required")
        private boolean multiSportsCourt;

        @NotNull(message = "Gym information is required")
        private boolean gym;

        @NotNull(message = "Steam room information is required")
        private boolean steamRoom;

        @NotNull(message = "Cable TV information is required")
        private boolean cableTV;

        @NotNull(message = "Heating information is required")
        private boolean heating;

        @NotNull(message = "Cabinets in the kitchen information is required")
        private boolean cabinetsInTheKitchen;

        @NotNull(message = "Bathroom in the room information is required")
        private boolean bathroomInTheRoom;

        @NotNull(message = "Internet information is required")
        private boolean internet;

        @NotNull(message = "Party room information is required")
        private boolean partyRoom;

        @NotNull(message = "Air conditioning information is required")
        private boolean airConditioning;

        @NotNull(message = "American kitchen information is required")
        private boolean americanKitchen;

        @NotNull(message = "Hydromassage information is required")
        private boolean hydromassage;

        @NotNull(message = "Fireplace information is required")
        private boolean fireplace;

        @NotNull(message = "Private pool information is required")
        private boolean privatePool;

        @NotNull(message = "Electronic gate information is required")
        private boolean electronicGate;

        @NotNull(message = "Service area information is required")
        private boolean serviceArea;

        @NotNull(message = "Pub information is required")
        private boolean pub;

        @NotNull(message = "Closet information is required")
        private boolean closet;

        @NotNull(message = "Office information is required")
        private boolean office;

        @NotNull(message = "Yard information is required")
        private boolean yard;

        @NotNull(message = "Alarm system information is required")
        private boolean alarmSystem;

        @NotNull(message = "Balcony information is required")
        private boolean balcony;

        @NotNull(message = "Concierge 24 hour information is required")
        private boolean concierge24Hour;

        @NotNull(message = "Walled area information is required")
        private boolean walledArea;

        @NotNull(message = "Dog allowed information is required")
        private boolean dogAllowed;

        @NotNull(message = "Cat allowed information is required")
        private boolean catAllowed;

        @NotNull(message = "Cameras information is required")
        private boolean cameras;

        @NotNull(message = "Furnished information is required")
        private boolean furnished;

        @NotNull(message = "Sea view information is required")
        private boolean seaView;

        @NotNull(message = "Gated community information is required")
        private boolean gatedCommunity;
}
