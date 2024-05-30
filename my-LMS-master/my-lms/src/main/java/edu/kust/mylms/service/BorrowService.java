package edu.kust.mylms.service;

import edu.kust.mylms.pojo.entity.Borrow;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.vo.BorrowsVo;
import edu.kust.mylms.pojo.vo.UserRecordVo;

public interface BorrowService {
    PageBean<BorrowsVo> borrows(Integer page, Integer pageSize);

    void addBorrow(Integer userId, Integer bookId);

    PageBean<UserRecordVo> userRecord(Integer page, Integer pageSize, Integer userId, Integer status);

    void returnBook(Integer borrowId);

}
