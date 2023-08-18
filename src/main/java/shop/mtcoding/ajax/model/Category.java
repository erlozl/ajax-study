package shop.mtcoding.ajax.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // DB 조회 -> PC에 Category객체 생성 -> 빈생성자를 호출
// 영속화가 안됨 , 빈생성자 없으면 터진다 , 나중에 테스트한번 해보기
@Getter
@Setter
@Table(name = "category_tb")
@Entity // 이걸 붙여야 hibernate관리 = entity manager가 관리한다는 뜻
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name; // 백엔드, 프론트엔드 카테고리 만드는 것임, DBA(데이터베이스 관리자)

    @Builder
    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
