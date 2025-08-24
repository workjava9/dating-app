{{- define "dating-app.name" -}}
dating-app
{{- end -}}

{{- define "dating-app.fullname" -}}
{{ include "dating-app.name" . }}
{{- end -}}
