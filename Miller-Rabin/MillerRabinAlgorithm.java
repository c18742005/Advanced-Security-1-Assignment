import java.math.BigInteger;
import java.util.Random;

/**
 * Advanced Security 1 - Assignment Part A 
 * Write a Java program which will test if the given number is a prime number or not
 * In order to achieve this you must implement the Miller-Rabin Algorithm
 * 
 * @author steven
 */
public class MillerRabinAlgorithm extends javax.swing.JFrame {

    /**
     * Creates new MillerRabinAlgorithm App
     */
    public MillerRabinAlgorithm() {
        initComponents();
    }
    
    /**
     * Function that performs the Miller-Rabin algorithm 
     * Prints INCONCLUSIVE if number is possibly a prime
     * Prints COMPOSITE if number is not a prime
     * @param n number to be tested for primality
     */
    public void millerRabinTest(BigInteger n) {
        // Base Cases
        // If n is 0 OR 1 then n is not prime
        if(n.equals(BigInteger.ONE) || n.equals(BigInteger.ZERO)) {
            resultText.setText("COMPOSITE - Number IS NOT prime");
            return;
        } 
        
        // If n is 2 OR 3 then n is prime
        if(n.equals(BigInteger.valueOf(2L)) || n.equals(BigInteger.valueOf(3L))) {
            resultText.setText("Number IS prime");
            return;
        } 
        
        // If n is even then n is not prime
        if(n.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
            resultText.setText("COMPOSITE - Number IS NOT prime");
            return;
        }	
			
        // Step 1: Find k & q where k > 0 and q is odd
        BigInteger q = n.subtract(BigInteger.ONE);
        BigInteger k = BigInteger.ZERO;
	
        while(q.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
            q = q.divide(BigInteger.valueOf(2L));
            k = k.add(BigInteger.ONE);
	    }
	
        // Step 2: Select random number a where 1 < a < n - 1
	    Random r = new Random();
	
        int rand = (n.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) 
                ? r.nextInt(Integer.MAX_VALUE - 2 - 2 + 1) + 2 
                : r.nextInt(n.intValue() - 2 - 2 + 1 ) + 2;
	
        BigInteger a = BigInteger.valueOf((long) rand);
        
	    // Step 3: Check if a^q mod n = 1
	    if(a.modPow(q, n).equals(BigInteger.ONE)) {
            resultText.setText("INCONCLUSIVE - Number IS POSSIBLY prime");
            return;
        }
	
        // Step 4: for j = 0 to k - 1 do
	    for(int j = 0; BigInteger.valueOf((long) j).compareTo(k) < 0; j++) {
            // Step 5: if a^(2^j)*q mod n = n - 1
            if(a.modPow(BigInteger.valueOf((long) 2).pow(j).multiply(q),n)
                    .equals(n.subtract(BigInteger.ONE))) {
                
                resultText.setText("INCONCLUSIVE - Number IS POSSIBLY prime");
                return;
            }
	    }
	
        resultText.setText("COMPOSITE - Number IS NOT prime");
    }
    
    /**
     *  Function to initialise components of the application
     */                         
    private void initComponents() {

        Heading = new javax.swing.JLabel();
        numberLabel = new javax.swing.JLabel();
        numberToTest = new javax.swing.JSpinner();
        result = new javax.swing.JScrollPane();
        resultText = new javax.swing.JTextArea();
        testButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Heading.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        Heading.setText("Miller Rabin Primality Test");

        numberLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        numberLabel.setText("Number to Test:");

        numberToTest.setModel(new javax.swing.SpinnerNumberModel());

        resultText.setColumns(20);
        resultText.setLineWrap(true);
        resultText.setRows(5);
        result.setViewportView(resultText);

        testButton.setBackground(new java.awt.Color(0, 102, 255));
        testButton.setForeground(new java.awt.Color(255, 255, 255));
        testButton.setText("Test");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Heading)
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numberToTest, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(testButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Heading)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberLabel)
                    .addComponent(numberToTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(testButton)
                .addGap(14, 14, 14)
                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }                     

    /**
     * Function that controls what happens when the test button is clicked
     * @param evt
     */
    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Get number to test for primality
        BigInteger n = BigInteger.valueOf((Integer) numberToTest.getValue());
        
        // Perform Miller-Rabin Test
        millerRabinTest(n);
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {   
        try {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch(ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MillerRabinAlgorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch(InstantiationException ex) {
            java.util.logging.Logger.getLogger(MillerRabinAlgorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch(IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MillerRabinAlgorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch(javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MillerRabinAlgorithm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the application */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MillerRabinAlgorithm().setVisible(true);
            }
        });
    }

    // Variables declaration                    
    private javax.swing.JLabel Heading;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JSpinner numberToTest;
    private javax.swing.JScrollPane result;
    private javax.swing.JTextArea resultText;
    private javax.swing.JButton testButton;                  
}
