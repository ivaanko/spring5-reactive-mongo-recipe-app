package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void fetchAllTest() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("Bucket");
        unitOfMeasureReactiveRepository.save(uom);
        Flux<UnitOfMeasure> unitOfMeasureFlux = unitOfMeasureReactiveRepository.findAll();
        unitOfMeasureFlux.subscribe(some -> { assertEquals("kaboom", "Bucket", some.getDescription()); });
    }

}