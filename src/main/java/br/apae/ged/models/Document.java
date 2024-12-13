package br.apae.ged.models;

import br.apae.ged.models.enums.TipoArquivo;
import br.apae.ged.models.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity(name = "tb_documentos")
@Table(indexes = {
        @Index(name = "titulo_idx", columnList = "titulo"),
        @Index(name = "aluno_idx", columnList = "aluno_id")
})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String path;
    private LocalDateTime dataUpload;
    private LocalDateTime dataDownload;
    private LocalDateTime dataUpdate;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Enumerated(EnumType.STRING)
    private TipoArquivo tipoArquivo;

    @ManyToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Alunos aluno;

    @ManyToOne
    @JoinColumn(name = "downloaded_by", referencedColumnName = "id")
    private User downloadedBy;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id")
    private User uploadedBy;

    @OneToOne
    @JoinColumn(name = "previous_version", referencedColumnName = "id")
    private Document prevVersion;

    private Boolean isLast;

    public Document(){
        this.dataUpload = LocalDateTime.now();
        this.isLast = true;
    }
}