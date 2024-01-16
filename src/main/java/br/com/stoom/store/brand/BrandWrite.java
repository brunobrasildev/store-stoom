package br.com.stoom.store.brand;

import br.com.stoom.store.core.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BrandWrite {

    private final BrandRepository repository;

    public Brand updateStatus(Long id, Status status) throws Exception {
        Brand brand = repository.findById(id)
                .orElseThrow(() -> new Exception("Brand not found"));
        brand.newStatus(status);

        return repository.save(brand);
    }

}
