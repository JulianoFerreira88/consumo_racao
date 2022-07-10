/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.consumo_racao.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class MainFrame extends javax.swing.JFrame {

    private final Connection con;
    private List<String> racoes;

    public MainFrame(Connection con) {
        this.con = con;
        initComponents();
        barChart2.setChartLabelsAngleDown_45();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRacoes = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        barChart2 = new com.github.consumo_racao.components.BarChart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(750, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(java.awt.Color.darkGray);
        jPanel2.setPreferredSize(new java.awt.Dimension(523, 250));

        listRacoes.setBackground(java.awt.Color.darkGray);
        listRacoes.setForeground(new java.awt.Color(0, 255, 204));
        listRacoes.setModel(getRacoesModel());
        listRacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listRacoes.setSelectionBackground(new java.awt.Color(255, 255, 255));
        listRacoes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listRacoes.setVisibleRowCount(4);
        listRacoes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listRacoesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listRacoes);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selecione o Tipo de Ração");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.CardLayout());

        barChart2.setChartColor(java.awt.Color.darkGray);
        barChart2.setChartLabelsColor(java.awt.Color.white);
        barChart2.setChartLabelsVisible(true);
        barChart2.setChartMarksColor(java.awt.Color.white);
        barChart2.setChartMarksVisible(true);
        barChart2.setChartSeriesColor(new java.awt.Color(0, 255, 204));
        barChart2.setChartSeriesVisibleOnLegend(false);
        barChart2.setChartTitleColor(java.awt.Color.white);
        jPanel3.add(barChart2, "card2");

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void listRacoesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listRacoesValueChanged
        onRacoesSelected(listRacoes.getSelectedValue());
    }//GEN-LAST:event_listRacoesValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.consumo_racao.components.BarChart barChart2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listRacoes;
    // End of variables declaration//GEN-END:variables

    private ListModel<String> getRacoesModel() {
        String sql = "select r.NMRACAO as RACAO\n"
                + "from EFABRACAO r\n"
                + "where r.CDRACAO in (\n"
                + "    select racao.cdracao \n"
                + "    from EFABLANCAMENTORACAO racao \n"
                + "    where racao.CDFASE = 4 \n"
                + ")";
        racoes = new ArrayList<>();
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            rs.first();
            while (!rs.isAfterLast()) {
                racoes.add(rs.getString("RACAO"));
                rs.next();
            }
        } catch (SQLException e) {
        }
        return new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return racoes.size();
            }

            @Override
            public String getElementAt(int i) {
                return racoes.get(i);
            }
        };
    }

    private void onRacoesSelected(String racao) {

        String sql = "select\n"
                + "extract(year from r.DTLANCAMENTO) as ANO,\n"
                + "racao.NMRACAO as RACAO,\n"
                + "sum(abs(r.QTRACAO))/(\n"
                + "    select \n"
                + "    sum(abs(m.QTANIMAIS))\n"
                + "    from ESANMOVANIMAIS m\n"
                + "    where m.CDFASE = 4\n"
                + "    and m.FLTIPO = 'F'\n"
                + "    and extract(year from m.DTMOVIMENTACAO) = extract(year from r.DTLANCAMENTO)\n"
                + ") as KG_ANIMAL\n"
                + "from EFABLANCAMENTORACAO r inner join EFABRACAO racao \n"
                + "on racao.CDRACAO = r.CDRACAO\n"
                + "where r.CDFASE = 4\n "
                + "and racao.NMRACAO = '" + racao + "'"
                + "and r.FLTIPOLANCAMENTO = 'U'\n"
                + "group by ANO,RACAO\n"
                + "order by ANO asc";

        try {

            JDBCCategoryDataset data = new JDBCCategoryDataset(con, sql);
            barChart2.setData(data);
            barChart2.setChartTitle("Kg de " + racao + "/Animal");

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
