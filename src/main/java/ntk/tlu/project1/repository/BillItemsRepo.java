package ntk.tlu.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntk.tlu.project1.entity.BillitemsEntity;

public interface BillItemsRepo extends JpaRepository<BillitemsEntity, Long> {
	@Query("SELECT u FROM BillitemsEntity u")
	List<BillitemsEntity> showBillitemsEntity();
}
