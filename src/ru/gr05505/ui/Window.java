package ru.gr05505.ui;

import ru.gr05505.domain.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Window extends JFrame implements BookView{
    private final JTextField tfAuthor = new JTextField();
    private final JTextField tfTitle = new JTextField();
    private final JTextField tfYear = new JTextField();
    private final JTextField tfAdditional = new JTextField();

    private final JButton btnAdd = new JButton("Добавить");

    private final JTextArea taBooks = new JTextArea(20, 50);

    private final BookPresenter presenter;
    public Window(BookPresenter presenter){
        this.presenter = presenter;
        setTitle("Книги");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));
        JPanel topPanel = new JPanel();
        GroupLayout layout = new GroupLayout(topPanel);
        topPanel.setLayout(layout);

        var addCommand = new AddBookCommand(presenter);

        var lblAuthor = new JLabel("Автор: ");
        var lblTitle = new JLabel("Название: ");
        var lblYear = new JLabel("Год издания: ");
        var lblAdditional = new JLabel("Дополнительно: ");

        var scrollPanel = new JScrollPane(taBooks);
        taBooks.setEditable(false);

        btnAdd.addActionListener(e -> {
            addCommand.execute();
        });

        tfAuthor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                presenter.inputChanged();
            }
        });
        tfTitle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                presenter.inputChanged();
            }
        });
        tfYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                presenter.inputChanged();
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(8)
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(lblAuthor)
                                        .addComponent(lblTitle)
                                        .addComponent(lblYear)
                                        .addComponent(lblAdditional)
                                )
                                .addGap(8)
                                .addGroup(
                                        layout.createParallelGroup()
                                                .addComponent(tfAuthor, DS, DS, DS)
                                                .addComponent(tfTitle, DS, DS, DS)
                                                .addComponent(tfYear, DS, DS, DS)
                                                .addComponent(tfAdditional, DS, DS, DS)
                                )
                                .addGap(8)
                                .addComponent(btnAdd)
                        )
                        .addComponent(scrollPanel, DS, DS, DS)
                )
                .addGap(8));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(8)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAuthor)
                                .addGap(8)
                                .addComponent(lblTitle)
                                .addGap(8)
                                .addComponent(lblYear)
                                .addGap(8)
                                .addComponent(lblAdditional))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tfAuthor)
                                .addGap(8)
                                .addComponent(tfTitle)
                                .addGap(8)
                                .addComponent(tfYear)
                                .addGap(8)
                                .addComponent(tfAdditional))
                        .addComponent(btnAdd))
                .addGap(8)
                .addComponent(scrollPanel, DS, DS, DS)
                .addGap(8)
        );

        add(topPanel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
    }

    private static final int DS = GroupLayout.DEFAULT_SIZE;

    @Override
    public String getAuthor() {
        return tfAuthor.getText();
    }

    @Override
    public String getBookTitle() {
        return tfTitle.getText();
    }

    @Override
    public String getYear() {
        return tfYear.getText();
    }

    @Override
    public String getAdditional() {
        return tfAdditional.getText();
    }

    @Override
    public void clearInput() {
        tfAdditional.setText("");
        tfAuthor.setText("");
        tfTitle.setText("");
        tfYear.setText("");
    }

    @Override
    public void showBooks(List<Book> books) {
        taBooks.setText("");
        for (var book : books) {
            taBooks.append(book.author());
            taBooks.append(" ");
            taBooks.append(book.title());
            taBooks.append(". ");
            taBooks.append(String.valueOf(book.year()));
            taBooks.append(". ");
            taBooks.append(book.additionalInfo());
            taBooks.append("\n");
        }
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this,message);
    }

    @Override
    public void render(State state) {
        btnAdd.setEnabled(state.addEnabled());
        if (state.error() != null) {
            showError(state.error());
        }
    }
}
