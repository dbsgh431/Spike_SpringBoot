package Spike.springboot.first.repository;

import Spike.springboot.first.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
