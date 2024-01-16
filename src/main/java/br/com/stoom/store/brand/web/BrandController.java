package br.com.stoom.store.brand.web;

import br.com.stoom.store.brand.Brand;
import br.com.stoom.store.brand.BrandWrite;
import br.com.stoom.store.core.enums.Status;
import br.com.stoom.store.core.dto.StatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandWrite white;

    @PatchMapping(value = "/{id}")
    public BrandResponse updateStatus(@PathVariable Long id, @Valid @RequestBody StatusRequest request) throws Exception {
        if (!Status.exists(request.getStatus())) {
            throw new Exception("Status not found");
        }

        Brand brand = white.updateStatus(id, Status.valueOf(request.getStatus()));

        return BrandResponse.of(brand);
    }

}
