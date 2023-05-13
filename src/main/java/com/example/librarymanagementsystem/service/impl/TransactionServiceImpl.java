package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.DTO.RequestDto.IssueBookRequestDto;
import com.example.librarymanagementsystem.DTO.ResponseDto.IssueBookResponseDto;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Card;
import com.example.librarymanagementsystem.entity.Transaction;
import com.example.librarymanagementsystem.enums.CardStauts;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CardRepoitory;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import com.example.librarymanagementsystem.service.TransactionService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    CardRepoitory cardRepoitory;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);
        Card card;
        try{
            card = cardRepoitory.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Invalid card id");
        }
        transaction.setCard(card);
        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book id");
        }
        transaction.setBook(book);
        if(card.getCardStatus() != CardStauts.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not Active");
        }
        if(book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not Available");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);
        book.setCard(card);
        book.getTransectionList().add(transaction);
        card.getBookIssued().add(book);
        card.getTransectionList().add(transaction);
        cardRepoitory.save(card);
        //let's make response dto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransectionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransectionStatus(transaction.getTransactionStatus());

        //it is part to send the email in real life
        String text = "Congrats bro ::"+card.getStudent().getName() + " you have been issued the book"+book.getTitle();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("mkparjapati9045@gmail.com");
        mailMessage.setTo(card.getStudent().getEmail());
        mailMessage.setSubject("Issue book!!!");
        mailMessage.setText(text);
        emailSender.send(mailMessage);
        return issueBookResponseDto;
    }
}
