import { Aluno } from "./aluno";
import { Skill } from "./skill"

export type Apresentam={
    skill:Skill;
    avaliacao_metrica:string;
    alunos:Aluno;
}