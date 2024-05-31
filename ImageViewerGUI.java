import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;


public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton= new JButton("choose Image");
    JButton showImageButton = new JButton("Show Image");
    JButton resizeButton = new JButton("Resize");
    JButton grayscaleButton = new JButton("Gray Scale");
    JButton brightnessButton = new JButton("Brightness");
    JButton closeButton = new JButton("Exit");
    JButton showResizeButton= new JButton("Result");   //result
    JButton showBrightnessButton =  new JButton("Result");;   //result
    JButton backButton =  new JButton("Back");
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField = new JTextField();
    String filePath = "/Users/maral/OneDrive/Desktop/photos ap";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;
    private Icon icon;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
        addActionLisListener();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(200,100,300,100);
        buttonsPanel.setLayout(new GridLayout(3, 2));



        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setSize(700,300);

        JLabel resizeSection = new JLabel("Resize Section");
        resizeSection.setBounds(300, 20, 100,50);

        JLabel width = new JLabel("Width");
        width.setBounds(200,70, 100, 50);
        widthTextField = new JTextField();
        widthTextField.setBounds(450, 70,80,40);

        JLabel Height = new JLabel("Height");
        Height.setBounds(200,140,100,50);
        heightTextField = new JTextField();
        heightTextField.setBounds(450,140,80,40);

//        backButton = new JButton("Back");
        backButton.setBounds(100, 200, 80,40);

//        showResizeButton = new JButton("Result");
        showResizeButton.setBounds(500, 200, 80,40);

        resizePanel.add(resizeSection);
        resizePanel.add(width);
        resizePanel.add(this.widthTextField);
        resizePanel.add(Height);
        resizePanel.add(this.heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);


        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.repaint();
        this.revalidate();
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);

        JLabel brightnessLabel = new JLabel("Enter f\n (must be between 0 and 1)");
        brightnessLabel.setBounds(100,80, 200,50);

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(400, 80, 200,50);

//        backButton = new JButton("Back");
        backButton.setBounds(115, 180, 100,38);


        showBrightnessButton.setBounds(475, 180, 100,38);



        brightnessPanel.add(brightnessLabel);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(backButton);
        brightnessPanel.add(showBrightnessButton);
        this.getContentPane().removeAll();


        this.add(brightnessPanel);
        this.repaint();
        this.revalidate();
    }

    public void chooseFileImage(){
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(ImageViewerGUI.this);
    }
    public void showOriginalImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel();
        file = fileChooser.getSelectedFile();
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        label.setIcon(imageIcon);


        tempPanel.add(label);
        label.setSize(900,500);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        tempPanel.setSize(1800,1000);

        JLabel label = new JLabel();
        file = fileChooser.getSelectedFile();


        BufferedImage bufferedImage = ImageIO.read(file);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = op.filter(bufferedImage, null);
        ImageIcon imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);


        tempPanel.add(label);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        file = fileChooser.getSelectedFile();
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = bufferedImage.getScaledInstance(w,h , Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel();
        label.setIcon(imageIcon);


        tempPanel.add(label);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f) throws IOException {

        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        file = fileChooser.getSelectedFile();

        BufferedImage bufferedImage = ImageIO.read(file);
        RescaleOp op = new RescaleOp(f,0, null);
        BufferedImage image = op.filter(bufferedImage, bufferedImage);

        ImageIcon imageIcon = new ImageIcon(image);
        label.setIcon(imageIcon);


        tempPanel.add(label);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            resizePanel();

        }else if(e.getSource()== showImageButton){
            try {
                showOriginalImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()==grayscaleButton){
            try {
                grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== showResizeButton){
            int h = Integer.parseInt(heightTextField.getText());
            int w = Integer.parseInt(widthTextField.getText());
            try {
                showResizeImage(w, h);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()==brightnessButton){
            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            try {
                float f = Float.parseFloat(brightnessTextField.getText());
                if (f <= 1 && f >= 0) {
                    showBrightnessImage(f);
                }
                else {
                    System.out.println("your brightness should be between 0 - 1");
                }
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }

    public void addActionLisListener(){
        resizeButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);
        showImageButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        selectFileButton.addActionListener(this);
        closeButton.addActionListener(this);
        backButton.addActionListener(this);
    }
}

