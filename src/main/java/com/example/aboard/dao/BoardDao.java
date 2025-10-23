package com.example.aboard.dao;

import com.example.aboard.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface BoardDao {
  @Insert("insert into board(bno,title,content,writer) values(board_seq.nextval, #{title},#{content},#{writer})")
  public int insert(Board board);

  public List<Board> findByPageno(long start, long end);
}
