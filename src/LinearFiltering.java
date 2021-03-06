/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leslie BAMOU
 */
public class LinearFiltering extends javax.swing.JPanel {

    /**
     * Creates new form LinearFiltering
     */
    public LinearFiltering() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        linearFilterChoicePanel = new javax.swing.JPanel();
        filterLinearChoiceLabel = new javax.swing.JLabel();
        filterLinearBorFillLabl = new javax.swing.JLabel();
        filterLinearDirLabel = new javax.swing.JLabel();
        filterLinearCombo = new javax.swing.JComboBox();
        errorMainDisplayScrollPane = new javax.swing.JScrollPane();
        linearFilterDisplayTextArea = new javax.swing.JTextArea();
        okLinearFilterLabel = new javax.swing.JLabel();
        okLinearFilterButton = new javax.swing.JButton();
        filterLinearDirCombo = new javax.swing.JComboBox();
        filterLinearBorderFillCombo = new javax.swing.JComboBox();
        logoLinearFilterLabel = new javax.swing.JLabel();
        linearFilerSeparator = new javax.swing.JSeparator();
        imageLinearFilterScrollPane = new javax.swing.JScrollPane();
        imageLinearFilterPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(1226, 528));
        setLayout(null);

        linearFilterChoicePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        linearFilterChoicePanel.setOpaque(false);

        filterLinearChoiceLabel.setText("Choose filter:");

        filterLinearBorFillLabl.setText("Border filling method:");

        filterLinearDirLabel.setText("Direction:");

        filterLinearCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Robert filter", "Sober filter", "Prewitt filter", "Kirsh filter" }));

        errorMainDisplayScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        linearFilterDisplayTextArea.setColumns(20);
        linearFilterDisplayTextArea.setFont(new java.awt.Font("Mongolian Baiti", 0, 14)); // NOI18N
        linearFilterDisplayTextArea.setForeground(new java.awt.Color(51, 153, 0));
        linearFilterDisplayTextArea.setLineWrap(true);
        linearFilterDisplayTextArea.setRows(5);
        linearFilterDisplayTextArea.setText("... Enter the characteristics for the            filtering process ...");
        linearFilterDisplayTextArea.setToolTipText("");
        errorMainDisplayScrollPane.setViewportView(linearFilterDisplayTextArea);

        okLinearFilterLabel.setText("Confirm:");

        okLinearFilterButton.setText("OK");
        okLinearFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okLinearFilterButtonActionPerformed(evt);
            }
        });

        filterLinearDirCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "X(horizontal)", "Y(vertical)", "45°", "90°" }));

        filterLinearBorderFillCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Zeros method", "Simple symmetry", "Circular symmetry" }));

        logoLinearFilterLabel.setForeground(new java.awt.Color(102, 102, 102));
        logoLinearFilterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLinearFilterLabel.setText("-------------------------  Input: Filtering process characteristic ---------------------------------------------------");

        javax.swing.GroupLayout linearFilterChoicePanelLayout = new javax.swing.GroupLayout(linearFilterChoicePanel);
        linearFilterChoicePanel.setLayout(linearFilterChoicePanelLayout);
        linearFilterChoicePanelLayout.setHorizontalGroup(
            linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(linearFilterChoicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(linearFilterChoicePanelLayout.createSequentialGroup()
                        .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterLinearChoiceLabel)
                            .addComponent(filterLinearDirLabel)
                            .addComponent(filterLinearBorFillLabl))
                        .addGap(18, 18, 18)
                        .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterLinearBorderFillCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(linearFilterChoicePanelLayout.createSequentialGroup()
                                .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filterLinearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(filterLinearDirCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(errorMainDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linearFilterChoicePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okLinearFilterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(okLinearFilterButton)
                        .addGap(75, 75, 75))))
            .addGroup(linearFilterChoicePanelLayout.createSequentialGroup()
                .addComponent(logoLinearFilterLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        linearFilterChoicePanelLayout.setVerticalGroup(
            linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linearFilterChoicePanelLayout.createSequentialGroup()
                .addComponent(logoLinearFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(linearFilterChoicePanelLayout.createSequentialGroup()
                        .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filterLinearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterLinearChoiceLabel))
                        .addGap(27, 27, 27)
                        .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filterLinearDirLabel)
                            .addComponent(filterLinearDirCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filterLinearBorFillLabl)
                            .addComponent(filterLinearBorderFillCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(errorMainDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(linearFilterChoicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okLinearFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okLinearFilterButton))
                .addGap(43, 43, 43))
        );

        add(linearFilterChoicePanel);
        linearFilterChoicePanel.setBounds(20, 180, 490, 250);
        linearFilterChoicePanel.getAccessibleContext().setAccessibleName("Input: Filtering process characteristic");

        linearFilerSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(linearFilerSeparator);
        linearFilerSeparator.setBounds(560, 10, 10, 520);

        imageLinearFilterPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imageLinearFilterPanelLayout = new javax.swing.GroupLayout(imageLinearFilterPanel);
        imageLinearFilterPanel.setLayout(imageLinearFilterPanelLayout);
        imageLinearFilterPanelLayout.setHorizontalGroup(
            imageLinearFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        imageLinearFilterPanelLayout.setVerticalGroup(
            imageLinearFilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        imageLinearFilterScrollPane.setViewportView(imageLinearFilterPanel);

        add(imageLinearFilterScrollPane);
        imageLinearFilterScrollPane.setBounds(590, 160, 620, 290);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pointCloudProcessing.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1230, 590);
    }// </editor-fold>//GEN-END:initComponents

    private void okLinearFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okLinearFilterButtonActionPerformed
            /*int bdrFll = filterLinearBorderFillCombo.getSelectedIndex();
            int dir = filterLinearDirCombo.getSelectedIndex();
            int fltChz = filterLinearCombo.getSelectedIndex();
    ImageFiltering imgFiltered = new ImageFiltering(original.getNL(), original.getNC(), original.getNB(), ifr, brdrFll, dir, fltChz);*/
    
        
        // TODO add your handling code here:
    }//GEN-LAST:event_okLinearFilterButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane errorMainDisplayScrollPane;
    private javax.swing.JLabel filterLinearBorFillLabl;
    private javax.swing.JComboBox filterLinearBorderFillCombo;
    private javax.swing.JLabel filterLinearChoiceLabel;
    private javax.swing.JComboBox filterLinearCombo;
    private javax.swing.JComboBox filterLinearDirCombo;
    private javax.swing.JLabel filterLinearDirLabel;
    private javax.swing.JPanel imageLinearFilterPanel;
    private javax.swing.JScrollPane imageLinearFilterScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator linearFilerSeparator;
    private javax.swing.JPanel linearFilterChoicePanel;
    private javax.swing.JTextArea linearFilterDisplayTextArea;
    private javax.swing.JLabel logoLinearFilterLabel;
    private javax.swing.JButton okLinearFilterButton;
    private javax.swing.JLabel okLinearFilterLabel;
    // End of variables declaration//GEN-END:variables
}
