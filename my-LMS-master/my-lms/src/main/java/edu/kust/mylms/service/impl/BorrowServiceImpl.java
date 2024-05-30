package edu.kust.mylms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.kust.mylms.mapper.BookMapper;
import edu.kust.mylms.mapper.BorrowMapper;
import edu.kust.mylms.pojo.entity.Book;
import edu.kust.mylms.pojo.entity.Borrow;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.vo.BorrowsVo;
import edu.kust.mylms.pojo.vo.UserRecordVo;
import edu.kust.mylms.service.BookService;
import edu.kust.mylms.service.BorrowService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;
    @Autowired
    public BorrowServiceImpl(BorrowMapper borrowMapper, BookMapper bookMapper) {
        this.borrowMapper = borrowMapper;
        this.bookMapper = bookMapper;
    }
    @Override
    public PageBean<BorrowsVo> borrows(Integer page, Integer pageSize) {
        PageBean<BorrowsVo> pageBean = new PageBean<>();
        PageHelper.startPage(page, pageSize);
        List<BorrowsVo> borrows = borrowMapper.borrows();
        Page<BorrowsVo> borrowPage = (Page<BorrowsVo>) borrows;
        pageBean.setTotal((int) borrowPage.getTotal());
        pageBean.setItems(borrows);
        return pageBean;
    }

    @Override
    @Transactional
    public void addBorrow(Integer userId, Integer bookId) {
        // 借书
        borrowMapper.addBorrow(userId,bookId);
        // 借书后减少书籍数量
        Book book = bookMapper.findById(bookId);
        // 判断书籍数量是否大于0
        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookMapper.update(book);
        } else {
            throw new RuntimeException("书籍数量不足");
        }
    }

    @Override
    public PageBean<UserRecordVo> userRecord(Integer page, Integer pageSize, Integer userId, Integer status) {
        PageBean<UserRecordVo> pageBean = new PageBean<>();
        PageHelper.startPage(page, pageSize);
        List<UserRecordVo> borrows = borrowMapper.userRecord(userId,status);
        Page<UserRecordVo> borrowPage = (Page<UserRecordVo>) borrows;
        pageBean.setTotal((int) borrowPage.getTotal());
        pageBean.setItems(borrows);
        return pageBean;
    }

    @Override
    @Transactional
    public void returnBook(Integer borrowId) {
        // 还书
        borrowMapper.returnBook(borrowId);
        Book book = bookMapper.findById(borrowMapper.findById(borrowId).getBookId());
        book.setQuantity(book.getQuantity() + 1);
        bookMapper.update(book);
    }

}
