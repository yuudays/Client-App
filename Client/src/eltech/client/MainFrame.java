package eltech.client;

import com.caucho.hessian.client.HessianProxyFactory;
import eltech.api.data.Movie;
import eltech.api.services.MovieService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;

import static java.awt.Color.*;


public class MainFrame extends JFrame {

    MainFrame() throws  HeadlessException{
        super("Movie Search");


        MovieList list = new MovieList();

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ImageIcon iconCard = new ImageIcon("./Client/resource/movie.png");

        JButton buttonAdd = new JButton();
        buttonAdd.setBackground(GREEN);
        buttonAdd.setIcon(setImageIconForButton("./Client/resource/add.png"));
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainFrame.this, "Название фильма", "Введите", JOptionPane.QUESTION_MESSAGE);
                String description = JOptionPane.showInputDialog(MainFrame.this,"Описание фильма","Введите",JOptionPane.QUESTION_MESSAGE);
                String director = JOptionPane.showInputDialog(MainFrame.this, "Режиссёр фильма", "Введите", JOptionPane.QUESTION_MESSAGE);
                if (name != null && description != null && director != null) {
                    Movie movie = new Movie();
                    movie.setName(name);
                    movie.setDirector(director);
                    movie.setDescription(description);
                    try {
                        String url = "http://127.0.0.1:8080/movie";
                        HessianProxyFactory factory = new HessianProxyFactory();
                        factory.setOverloadEnabled(true);
                        MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                        String id = movieService.addMovie(movie);
                        Movie movie1 = movieService.getMovie(id);
                        list.getMovieModel().addMovie(movie1);

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });



        JButton buttonDelete = new JButton();
        buttonDelete.setBackground(RED);
        buttonDelete.setIcon(setImageIconForButton("./Client/resource/delete.png"));
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null, "Удаление текущего фильма",
                        "", JOptionPane.PLAIN_MESSAGE);
                Movie movie=list.getSelectedValue();
                try {
                    String url = "http://127.0.0.1:8080/movie";
                    HessianProxyFactory factory = new HessianProxyFactory();
                    factory.setOverloadEnabled(true);
                    MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                    movieService.deleteMovie(movie.getId());
                    list.getMovieModel().deleteMovie(movie);
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JTabbedPane tabbedPane = new JTabbedPane();

        JTextArea movieDescription = new JTextArea();
        movieDescription.setLayout(new FlowLayout());
        movieDescription.setEditable(false);


        tabbedPane.addTab("Информация о фильме",movieDescription);



        JPanel changePanel = new JPanel(new FlowLayout());

        JButton changeNameButton = new JButton("Изменить название");
        changeNameButton.setBackground(ORANGE);
        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainFrame.this, "Название фильма", "Введите", JOptionPane.QUESTION_MESSAGE);
                Movie movie;
                movie = list.getSelectedValue();
                int index=list.getSelectedIndex();
                if(name!=null){
                    try {
                        String url = "http://127.0.0.1:8080/movie";
                        HessianProxyFactory factory = new HessianProxyFactory();
                        factory.setOverloadEnabled(true);
                        MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                        String id = movie.getId();
                        movieService.changeName(id,name);
                        movie.setName(name);
                        list.getMovieModel().setMovie(index,movie);

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        JButton changeDirectorButton = new JButton("Изменить режиссёра");
        changeDirectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String director = JOptionPane.showInputDialog(MainFrame.this, "Режиссёр фильма", "Введите", JOptionPane.QUESTION_MESSAGE);
                Movie movie;
                movie = list.getSelectedValue();
                int index=list.getSelectedIndex();
                if(director!=null){
                    try {
                        String url = "http://127.0.0.1:8080/movie";
                        HessianProxyFactory factory = new HessianProxyFactory();
                        factory.setOverloadEnabled(true);
                        MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                        String id = movie.getId();
                        movieService.changeDirector(id,director);
                        movie.setDirector(director);
                        list.getMovieModel().setMovie(index,movie);

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        changeDirectorButton.setBackground(ORANGE);

        JButton changeDescriptionButton = new JButton("Изменить описание");
        changeDescriptionButton.setBackground(ORANGE);
        changeDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = JOptionPane.showInputDialog(MainFrame.this, "Описание фильма", "Введите", JOptionPane.QUESTION_MESSAGE);
                Movie movie;
                movie = list.getSelectedValue();
                int index=list.getSelectedIndex();
                if(description!=null){
                    try {
                        String url = "http://127.0.0.1:8080/movie";
                        HessianProxyFactory factory = new HessianProxyFactory();
                        factory.setOverloadEnabled(true);
                        MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                        String id = movie.getId();
                        movieService.changeDescription(id,description);
                        movie.setDescription(description);
                        list.getMovieModel().setMovie(index,movie);

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        changePanel.add(changeDirectorButton);
        changePanel.add(changeDescriptionButton);
        changePanel.add(changeNameButton);
        tabbedPane.addTab("Изменение информации о фильме",changePanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonDelete);
        tabbedPane.addTab("Добавить/Удалить фильм",buttonPanel);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(list),BorderLayout.NORTH);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selected = ((JList<?>)e.getSource()).getSelectedIndex();
            }
        });
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selected = list.locationToIndex(e.getPoint());
                Movie movie = list.getSelectedValue();
                String info = "Название:"+movie.getName()+
                        "\n"+"Режиссёр:"+movie.getDirector()+
                        "\n"+movie.getDescription();
                movieDescription.setText(info);
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });

        tabbedPane.addTab("Список фильмов",listPanel);






        JMenu menu2 = new JMenu("Меню");

        JMenuItem showDevs=new JMenuItem("Авторы");
        showDevs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalMessageDialog(null,"Разработано студентами группы 1391 Гречишиновым А.Е. и Мец К.В.",
                        "Информация",JOptionPane.PLAIN_MESSAGE);
            }
        });
        JMenuItem itemExit= new JMenuItem("Выход");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu2.add(showDevs);
        menu2.add(itemExit);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu2);
        menuBar.setVisible(true);


        JLabel nameLabel = new JLabel("Имя фильма:");
        JLabel directorLabel = new JLabel("Режиссёр:");

        JTextField nameTextField = new JTextField(6);
        JTextField dirTextField = new JTextField(6);


        JButton btnFind = new JButton("Найти");
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String dir = dirTextField.getText();
                if(name!=null && dir!=null){
                    try{
                        String url = "http://127.0.0.1:8080/movie";
                        HessianProxyFactory factory = new HessianProxyFactory();
                        factory.setOverloadEnabled(true);
                        MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                        Movie movie = movieService.searchMovie(name,dir);
                        if(movie!=null){
                            JOptionPane.showInternalMessageDialog(null,"Название фильма: "+movie.getName()+
                                    "\nРежиссёр: "+movie.getDirector()+"\nОписание: " + movie.getDescription(),
                                    "Результаты поиска",JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showInternalMessageDialog(null,"Такой фильм не найден","Результаты",JOptionPane.PLAIN_MESSAGE);
                        }

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        JButton connectButton = new JButton("Подключиться к базе фильмов");
        connectButton.setLayout(null);
        connectButton.setBounds(100,100,100, 100);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String url = "http://127.0.0.1:8080/movie";
                    HessianProxyFactory factory = new HessianProxyFactory();
                    factory.setOverloadEnabled(true);
                    MovieService movieService = (MovieService) factory.create(MovieService.class, url);
                    list.getMovieModel().setMovieList(movieService.getMovieList());
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.add(nameLabel);
        rightPanel.add(nameTextField);
        rightPanel.add(directorLabel);
        rightPanel.add(dirTextField);
        rightPanel.add(btnFind);
        rightPanel.add(connectButton);



        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tabbedPane,BorderLayout.CENTER);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel,rightPanel);
        splitPane.setDividerLocation(800);
        splitPane.setOneTouchExpandable(true);


        this.add(menuBar,BorderLayout.NORTH);
        this.setIconImage(iconCard.getImage());
        this.add(splitPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


    }


    private ImageIcon setImageIconForButton(String iconPath){
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image scaledIcon = icon.getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledIcon);
        }catch(NullPointerException e) {
            throw new RuntimeException(e);
        }
    }


//    public class JPanelWithBackground extends JPanel {
//        private Image backgroundImage;
//        public JPanelWithBackground(String fileName) throws IOException {
//            backgroundImage = ImageIO.read(new File(fileName));
//        }
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            // Draw the background image.
//            g.drawImage(backgroundImage, 0, 0, this);
//        }
//    }


}

