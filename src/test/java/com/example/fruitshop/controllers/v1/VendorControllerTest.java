package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.controllers.RestResponseEntityExceptionHandler;
import com.example.fruitshop.domain.Vendor;
import com.example.fruitshop.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static com.example.fruitshop.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static com.example.fruitshop.controllers.v1.VendorController.BASE_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mvc;

    private Long id = 1L;
    private String name = "testName";

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllVendors() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setUrl(getVendorUrl(id));
        vendor1.setName(name);

        final Long id2 = id + 1;
        final String name2 = name + 2;
        VendorDTO vendor2 = new VendorDTO();
        vendor2.setUrl(getVendorUrl(id));
        vendor2.setName(name2);

        when(vendorService.getAllVendors()).thenReturn(Arrays.asList(vendor1, vendor2));

        mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void getVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setUrl(getVendorUrl(id));
        vendorDTO.setName(name);

        when(vendorService.getVendor(id)).thenReturn(vendorDTO);

        mvc.perform(get(getVendorUrl(id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(id))));
    }

    @Test
    void createNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(name);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setUrl(getVendorUrl(id));
        returnVendor.setName(name);

        when(vendorService.createNewVendorDTO(any(VendorDTO.class))).thenReturn(returnVendor);

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(id))));
    }

    @Test
    void saveVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();;
        vendorDTO.setName(name);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setUrl(getVendorUrl(id));
        returnVendor.setName(name);

        when(vendorService.saveVendorDTO(anyLong() ,any(VendorDTO.class))).thenReturn(returnVendor);

        mvc.perform(put(getVendorUrl(id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(id))));
    }

    @Test
    void patchVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(name);

        VendorDTO returnVendor = new VendorDTO();
        returnVendor.setUrl(getVendorUrl(id));
        returnVendor.setName(name);

        when(vendorService.patchVendorDTO(anyLong(), any(VendorDTO.class))).thenReturn(returnVendor);

        mvc.perform(patch(getVendorUrl(id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(name)))
                .andExpect(jsonPath("$.vendor_url", equalTo(getVendorUrl(id))));
    }

    @Test
    void deleteVendor() throws Exception {
        mvc.perform(delete(getVendorUrl(id))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService, times(1)).deleteVendor(id);
    }

    private String getVendorUrl(Long id) {
        return BASE_URL + "/" + id;
    }
}