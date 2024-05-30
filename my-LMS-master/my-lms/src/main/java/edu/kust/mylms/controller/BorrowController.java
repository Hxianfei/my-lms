package edu.kust.mylms.controller;

import edu.kust.mylms.pojo.entity.Borrow;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.result.Result;
import edu.kust.mylms.pojo.vo.BorrowsVo;
import edu.kust.mylms.pojo.vo.UserRecordVo;
import edu.kust.mylms.service.BorrowService;
import edu.kust.mylms.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/borrow")
@Validated
@Slf4j
public class BorrowController {
    private final BorrowService borrowService;
    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    // 借书信息
    @GetMapping()
    public Result<PageBean<BorrowsVo>> borrows(Integer page, Integer pageSize) {
        log.info("查询借书信息");
        return Result.success(borrowService.borrows(page, pageSize));
    }

    // 借书
    @GetMapping("/add")
    public Result borrow(Integer id) {
        log.info("借书: 参数=>" + id);
        Integer bookId = id;
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        borrowService.addBorrow(userId, bookId);
        return Result.success();
    }

    // 还书
    @GetMapping("/return")
    public Result returnBook(Integer id) {
        log.info("还书: 参数=>" + id);
        Integer borrowId = id;
        borrowService.returnBook(borrowId);
        return Result.success();
    }

    // 用户借阅记录（已归还）
    @GetMapping("/userRecord")
    public Result<PageBean<UserRecordVo>> userRecord(Integer page, Integer pageSize) {
        log.info("查询用户借书信息: 参数=>" + page + ", " + pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return Result.success(borrowService.userRecord(page, pageSize, userId,1)); // 1表示已归还
    }

    // 用户借阅记录（未归还）
    @GetMapping("/userBorrowed")
    public Result<PageBean<UserRecordVo>> userBorrowed(Integer page, Integer pageSize) {
        log.info("查询用户借书信息: 参数=>" + page + ", " + pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return Result.success(borrowService.userRecord(page, pageSize, userId, 0)); // 0表示未归还
    }
}
