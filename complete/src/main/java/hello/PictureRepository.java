package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "picture", path = "picture")
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {

	List<Picture> findByMetadata(@Param("metadata") String metadata);

}
