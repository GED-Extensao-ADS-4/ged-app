package br.apae.ged.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "tb_documentos")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String tipoDocumento;
    private String tipoArquivo;
    private String path;
    private LocalDateTime dataUpload;
    private LocalDateTime dataDownload;
    private String downloadedBy;
    private String uploadedBy;
    @OneToOne
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private Document prevVersion;
    private Boolean isLast;

    public Document(){
        this.dataUpload = LocalDateTime.now();
        this.isLast = true;
    }
}
