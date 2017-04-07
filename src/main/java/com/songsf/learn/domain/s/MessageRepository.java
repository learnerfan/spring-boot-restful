package com.songsf.learn.domain.s;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.didispace.com
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
