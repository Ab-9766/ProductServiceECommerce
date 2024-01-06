package com.example.productservicedec20.Services;
import com.example.productservicedec20.DTOs.FakeStoreDTO;
import com.example.productservicedec20.Models.Category;
import com.example.productservicedec20.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreDTO fakeStoreDTO){
        Product product=new Product();
        product.setTitle(fakeStoreDTO.getTitle());
        product.setId(fakeStoreDTO.getId());
        product.setTitle(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreDTO.getCategory());
        product.setDescription(fakeStoreDTO.getDescription());

        return product;
    }
@Override
    public Product getSingleProduct(Long id) {
    FakeStoreDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
            FakeStoreDTO.class
    );
    return convertFakeStoreProductToProduct(productDTO);
}

    @Override
    public List<Product> getAllProduct() {
        FakeStoreDTO[] response= restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDTO[].class);

        List<Product> answer= new ArrayList<>();
        for(FakeStoreDTO dto:response)
        {
            answer.add(convertFakeStoreProductToProduct(dto));
        }
        return answer;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreDTO fakeStoreDTO= new FakeStoreDTO();
        fakeStoreDTO.setTitle(product.getTitle());
        fakeStoreDTO.setTitle(product.getTitle());
        fakeStoreDTO.setPrice(product.getPrice());
        fakeStoreDTO.setDescription(product.getDescription());
        RequestCallback requestCallback= restTemplate.httpEntityCallback(fakeStoreDTO
                ,FakeStoreDTO.class);
        HttpMessageConverterExtractor<FakeStoreDTO> responseExtractor= new HttpMessageConverterExtractor<>
                (FakeStoreDTO.class,restTemplate.getMessageConverters());

        FakeStoreDTO response= restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,requestCallback,responseExtractor);
        return convertFakeStoreProductToProduct(response);
    }
}
