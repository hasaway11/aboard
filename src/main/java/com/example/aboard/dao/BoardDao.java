package com.example.aboard.dao;

import com.example.aboard.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface BoardDao {
  public long count();

  public List<Board> findByPageno(long start, long end);

  @Select("select * from board where bno=#{bno}")
  Board findByBno(Long bno);

  @Update("update board set read_cnt=read_cnt+1 where bno=#{bno}")
  int increaseReadCnt(Long bno);

  @Select("select writer from board where bno=#{bno}")
  String findWriterByBno(Long bno);

  @Delete("delete from board where bno=#{bno}")
  int delete(Long bno);

  // @Insert전에 @SelectKey를 먼저 실행해서 bno를 생성
  // 그 bno를 파라미터 board의 bno에 집어넣자
  // @Insert할 때는 시퀀스 대신 board.bno를 사용한다
  // 그리고 서비스에서 board.getBno()를 해보면 아까 생성한 bno가 들어있다
  @SelectKey(statement="select board_seq.nextval from dual", before=true, keyProperty="bno", resultType=Long.class)
  @Insert("insert into board(bno,title,content,writer) values(#{bno},#{title},#{content},#{writer})")
  int insert(Board board);

}
